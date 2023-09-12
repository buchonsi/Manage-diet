package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.recipe.dto.RecipeDto;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietOutputDto {
    private TypeOfTime typeOfTime;
    private LocalDateTime dietAppliedDate;
    private List<Recipe> recipeList;

    private double dietTotalCalorie;

    public static DietOutputDto of(List<Diet> dietList) {
        if (dietList == null || dietList.isEmpty()) {
            throw new IllegalArgumentException("식단이 없습니다. 식단 설정을 해주세요.");
        }

        List<Recipe> recipeList = new ArrayList<>();
        BigDecimal totalCalorie = BigDecimal.ZERO;

        for (Diet diet : dietList) {
            recipeList.add(diet.getRecipe());
            BigDecimal calorie = BigDecimal.valueOf(diet.getRecipe().getCalorie());
            totalCalorie = totalCalorie.add(calorie);
        }

        return DietOutputDto.builder()
                .typeOfTime(dietList.get(0).getTypeOfTime())
                .dietAppliedDate(dietList.get(0).getDietAppliedDate())
                .recipeList(recipeList)
                .dietTotalCalorie(totalCalorie.doubleValue())
                .build();
    }
}
