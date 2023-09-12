package com.yoons.managediet.diet.service;

import com.yoons.managediet.diet.dto.DietInputDto;
import com.yoons.managediet.diet.dto.DietOutputDto;
import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.repository.DietRepository;
import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.respository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AnalyzeDailyDietService {
    private final DietRepository dietRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public DietOutputDto saveAll(DietInputDto dietInputDto) {
        //validate
        if (validateDailyDiet(dietInputDto)) {
            //exception 처리
        }

        List<Diet> savedDietList = dietRepository.saveAll(convertToDiet(dietInputDto));
        return DietOutputDto.of(savedDietList);
    }

    private boolean validateDailyDiet(DietInputDto dietInputDto) {
        return CollectionUtils.isEmpty(dietInputDto.getRecipeList());
    }

    private List<Diet> convertToDiet(DietInputDto dietInputDto) {
        List<Diet> dietList = new ArrayList<>();

        for (Long recipeId : dietInputDto.getRecipeList()) {
            Recipe recipe = recipeRepository.findById(recipeId)
                        .orElseThrow(() -> new RuntimeException("레시피를 찾을 수 없습니다: " + recipeId));

            dietList.add(dietInputDto.toEntity(recipe));
        }
        return dietList;
    }

    private double sumDailyCalorie(List<Double> calorieList) {
        return calorieList.stream().map(BigDecimal::valueOf).reduce(BigDecimal.ZERO, BigDecimal::add).doubleValue();
    }

    private double subtractCalorie(double targetCalorie, double dietTotalCalorie) {
        BigDecimal targetValue = BigDecimal.valueOf(targetCalorie);
        BigDecimal dietTotalValue = BigDecimal.valueOf(dietTotalCalorie);
        BigDecimal result = targetValue.subtract(dietTotalValue);
        return result.doubleValue();
    }
}
