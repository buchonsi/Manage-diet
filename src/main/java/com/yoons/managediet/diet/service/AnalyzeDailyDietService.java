package com.yoons.managediet.diet.service;

import com.yoons.managediet.diet.dto.DailyOutputDto;
import com.yoons.managediet.diet.dto.DietSaveInputDto;
import com.yoons.managediet.diet.dto.DietOutputDto;
import com.yoons.managediet.diet.dto.DietRecipeOutputDto;
import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.diet.repository.DietRepository;
import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.respository.RecipeRepository;
import com.yoons.managediet.recipe.service.RecipeRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.*;

@Service
@RequiredArgsConstructor
public class AnalyzeDailyDietService {
    private final DietRepository dietRepository;
    private final DietRepositoryService dietRepositoryService;
    private final RecipeRepositoryService recipeRepositoryService;
    private final RecipeRepository recipeRepository;

    @Transactional
    public DietOutputDto saveDiet(DietSaveInputDto dietSaveInputDto) {
        //validate
        if (validateDailyDiet(dietSaveInputDto)) {
            //@TODO exception 처리
        }

        //삭제하고
        deleteDietByDateAndDaily(dietSaveInputDto);

        //저장
        List<Diet> savedDietList = dietRepositoryService.saveAll(convertToDiet(dietSaveInputDto));
        //@Todo 개선점 -> totalCalorie를 다시 계산해서 outputDto로 변환해야하는지
        return DietOutputDto.of(
                dietSaveInputDto.getDietAppliedDate(),
                dietSaveInputDto.getTypeOfTime(),
                getRecipeAndCalorie(savedDietList)
                );
    }

    public void deleteDietByDateAndDaily(DietSaveInputDto dietSaveInputDto) {
        dietRepositoryService.deleteAllByDailyAndDate(dietSaveInputDto.getDietAppliedDate().format(DateTimeFormatter.ISO_DATE), dietSaveInputDto.getTypeOfTime().name());
    }

    public DailyOutputDto getOneDayDiet(LocalDate localDate) {
        //@Todo 다시
        List<Diet> oneDayDietList = dietRepositoryService.findByDietAppliedDate(localDate);

        Map<TypeOfTime, List<Diet>> groupedOneDayDietMap = classifyTime(oneDayDietList);

        Map<TypeOfTime, Double> groupedDietTotalCalorie = groupedOneDayDietMap.entrySet().stream()
                .collect(
                        toMap(Map.Entry::getKey,
                                map -> map.getValue().stream()
                                        .mapToDouble(diet -> diet.getRecipe().getCalorie()).sum()
                        )
                );
        double oneDayTotalCalorie = groupedDietTotalCalorie.values().stream().mapToDouble(value -> value).sum();

//        Map<TypeOfTime, DoubleSummaryStatistics> groupedDietMapByTime = classifyTime(oneDayDietList);
//        Map<TypeOfTime, Double> groupedDietTotalCalorie =
//                groupedDietMapByTime.entrySet().stream()
//                        .collect(toMap(Map.Entry::getKey, summary -> summary.getValue().getSum()));
//
//        double oneDayTotalCalorie = groupedDietTotalCalorie.values().stream()
//                .mapToDouble(Double::doubleValue).sum();

        return DailyOutputDto.of(localDate, oneDayTotalCalorie, groupedDietTotalCalorie);
    }

    public Map<TypeOfTime, List<Diet>> classifyTime(List<Diet> dietList) {
        return dietList.stream()
                .collect(groupingBy(Diet::getTypeOfTime));
//                .collect(
//                        groupingBy(Diet::getTypeOfTime,
//                                summarizingDouble(diet -> diet.getRecipe().getCalorie()))
//                );
    }

    public DietOutputDto getPartDiet(LocalDate localDate, TypeOfTime typeOfTime) {
        List<Diet> dietList = dietRepositoryService.findByDietAppliedDateAndTypeOfTime(localDate, typeOfTime);

        DietRecipeOutputDto dietRecipeOutputDto = getRecipeAndCalorie(dietList);

        return DietOutputDto.of(localDate, typeOfTime, dietRecipeOutputDto);
    }

    private DietRecipeOutputDto getRecipeAndCalorie(List<Diet> dietList) {
        List<Recipe> recipeList = dietList.stream().map(Diet::getRecipe).collect(toList());
        double totalCalorie = dietList.stream().mapToDouble(diet -> diet.getRecipe().getCalorie()).sum();

        return DietRecipeOutputDto.of(totalCalorie, recipeList);
    }


    private boolean validateDailyDiet(DietSaveInputDto dietSaveInputDto) {
        return Objects.isNull(dietSaveInputDto);
    }

    private List<Diet> convertToDiet(DietSaveInputDto dietSaveInputDto) {

        if (CollectionUtils.isEmpty(dietSaveInputDto.getRecipeList())) {
            return List.of(dietSaveInputDto.toEntity(null));
        }

        return dietSaveInputDto.getRecipeList().stream()
                .map(recipeRepositoryService::findById)
                .map(dietSaveInputDto::toEntity)
                .collect(toList());
    }

    //@TODO 속도 테스트용
//    private double sumDietCalorie(List<Diet> dietList) {
//        return dietList.stream()
//                .map(diet -> diet.getRecipe().getCalorie())
//                .map(BigDecimal::valueOf)
//                .reduce(BigDecimal.ZERO, BigDecimal::add)
//                .doubleValue();
//    }
//
//    private double subtractCalorie(double targetCalorie, double dietTotalCalorie) {
//        BigDecimal targetValue = BigDecimal.valueOf(targetCalorie);
//        BigDecimal dietTotalValue = BigDecimal.valueOf(dietTotalCalorie);
//        BigDecimal result = targetValue.subtract(dietTotalValue);
//        return result.doubleValue();
//    }
}
