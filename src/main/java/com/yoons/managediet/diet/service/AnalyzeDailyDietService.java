package com.yoons.managediet.diet.service;

import com.yoons.managediet.diet.dto.DailyInputDto;
import com.yoons.managediet.diet.dto.DailyOutputDto;
import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.diet.repository.DietRepository;
import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.service.RecipeRecommendService;
import com.yoons.managediet.recipe.service.RecipeRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AnalyzeDailyDietService {
    private final RecipeRecommendService recipeRecommendService;
    private final RecipeRepositoryService recipeRepositoryService;
    private final DietRepository dietRepository;

    @Transactional
    public DailyOutputDto save(DailyInputDto dailyInputDto) {
        //validate
        if (validateDailyDiet(dailyInputDto)) {
            //exception?
        }

        Diet savedDiet = dietRepository.save(convertToDiet(dailyInputDto));
        return convertToDailyOutputDto(savedDiet);
    }

    private Map<String, Recipe> getDailyRecipes(DailyInputDto dailyInputDto) {
        Map<String, Recipe> recipeMap = new HashMap<>();
        recipeMap.put(TypeOfTime.MORNING.name(), recipeRepositoryService.findById(dailyInputDto.getMorningRecipeId()));
        recipeMap.put(TypeOfTime.AFTERNOON.name(), recipeRepositoryService.findById(dailyInputDto.getAfternoonRecipeId()));
        recipeMap.put(TypeOfTime.NIGHT.name(), recipeRepositoryService.findById(dailyInputDto.getNightRecipeId()));
        return recipeMap;
    }

    private boolean validateDailyDiet(DailyInputDto dailyInputDto) {
        return Objects.isNull(dailyInputDto.getMorningRecipeId()) ||
                Objects.isNull(dailyInputDto.getAfternoonRecipeId()) ||
                Objects.isNull(dailyInputDto.getNightRecipeId());
    }

    private Diet convertToDiet(DailyInputDto dailyInputDto) {
        Recipe morningRecipe = recipeRepositoryService.findById(dailyInputDto.getMorningRecipeId());
        Recipe afternoonRecipe = recipeRepositoryService.findById(dailyInputDto.getAfternoonRecipeId());
        Recipe nightRecipe = recipeRepositoryService.findById(dailyInputDto.getNightRecipeId());

        double targetCalorie = dailyInputDto.getTargetCalorie();
        double dietTotalCalorie = sumDailyCalorie(morningRecipe.getCalorie(), afternoonRecipe.getCalorie(), nightRecipe.getCalorie());
        double remainedCalorie = subtractCalorie(targetCalorie, dietTotalCalorie);

        return Diet.builder()
                .targetCalorie(targetCalorie)
                .dietTotalCalorie(dietTotalCalorie)
                .remainedCalorie(remainedCalorie)
                .morningRecipe(morningRecipe)
                .afternoonRecipe(afternoonRecipe)
                .nightRecipe(nightRecipe)
                .dietAppliedDate(dailyInputDto.getDietAppliedDate())
                .build();
    }

    private DailyOutputDto convertToDailyOutputDto(Diet diet) {
        return DailyOutputDto.builder()
                .id(diet.getId())
                .targetCalorie(diet.getTargetCalorie())
                .dietTotalCalorie(diet.getDietTotalCalorie())
                .remainedCalorie(diet.getRemainedCalorie())
                .morningRecipe(diet.getMorningRecipe())
                .afternoonRecipe(diet.getAfternoonRecipe())
                .nightRecipe(diet.getNightRecipe())
                .dietAppliedDate(diet.getDietAppliedDate())
                .build();
    }

    private double sumDailyCalorie(double morningCalorie, double afternoonCalorie, double nightCalorie) {
        BigDecimal morningValue = BigDecimal.valueOf(morningCalorie);
        BigDecimal afternoonValue = BigDecimal.valueOf(afternoonCalorie);
        BigDecimal nightValue = BigDecimal.valueOf(nightCalorie);
        BigDecimal result = morningValue.add(afternoonValue).add(nightValue);

        return result.doubleValue();
    }

    private double subtractCalorie(double targetCalorie, double dietTotalCalorie) {
        BigDecimal targetValue = BigDecimal.valueOf(targetCalorie);
        BigDecimal dietTotalValue = BigDecimal.valueOf(dietTotalCalorie);
        BigDecimal result = targetValue.subtract(dietTotalValue);
        return result.doubleValue();
    }
}
