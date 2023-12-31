package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DietSaveInputDto {
    //@TODO 지워도 될 것 같음
    private double totalCalorie;
    private List<Long> recipeList;

    private LocalDate dietAppliedDate;
    private TypeOfTime typeOfTime;

    public Diet toEntity(Recipe recipe) {
        return Diet.builder()
                .dietAppliedDate(this.dietAppliedDate)
                .typeOfTime(this.typeOfTime)
                .recipe(recipe)
                .build();
    }
}
