package com.yoons.managediet.diet.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietInputDto {
    private double totalCalorie;
    private List<Long> recipeList;

    @JsonFormat(pattern = "YYYY-MM-DD")
    private LocalDateTime dietAppliedDate;
    private TypeOfTime typeOfTime;

    public Diet toEntity(Recipe recipe) {
        return Diet.builder()
                .dietAppliedDate(this.dietAppliedDate)
                .typeOfTime(this.typeOfTime)
                .recipe(recipe)
                .build();
    }
}
