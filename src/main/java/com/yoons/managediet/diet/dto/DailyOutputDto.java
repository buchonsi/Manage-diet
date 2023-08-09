package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DailyOutputDto {
    private Long id;
    private TypeOfTime typeOfTime;
    private Recipe recipe;
    private LocalDateTime dietAppliedDate;

    private double totalCalorie;
}
