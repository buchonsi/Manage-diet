package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyOutputDto {
    private Long id;

//    private TypeOfTime typeOfTime;

    private Recipe morningRecipe;
    private Recipe afternoonRecipe;
    private Recipe nightRecipe;
    private LocalDateTime dietAppliedDate;

    private double targetCalorie;
    private double dietTotalCalorie;
    private double remainedCalorie;
}
