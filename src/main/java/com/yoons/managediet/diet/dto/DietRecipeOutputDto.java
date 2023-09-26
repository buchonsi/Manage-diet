package com.yoons.managediet.diet.dto;

import com.yoons.managediet.recipe.entity.Recipe;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DietRecipeOutputDto {
    private double dietTotalCalorie;
    private List<Recipe> recipeList;

    public static DietRecipeOutputDto of(double dietTotalCalorie, List<Recipe> recipeList) {
        return DietRecipeOutputDto.builder()
                .dietTotalCalorie(dietTotalCalorie)
                .recipeList(recipeList)
                .build();
    }
}
