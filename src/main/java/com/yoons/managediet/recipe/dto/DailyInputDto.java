package com.yoons.managediet.recipe.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyInputDto {
    private double calorie;
    private Long morningRecipeId;
    private Long afternoonRecipeId;
    private Long nightRecipeId;
}
