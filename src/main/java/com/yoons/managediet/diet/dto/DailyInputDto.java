package com.yoons.managediet.diet.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DailyInputDto {
    private double calorie;
    private Long morningRecipeId;
    private Long afternoonRecipeId;
    private Long nightRecipeId;
    private LocalDateTime dietAppliedDate;
}
