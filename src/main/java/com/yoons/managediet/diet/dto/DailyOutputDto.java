package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.TypeOfTime;
import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DailyOutputDto {
    private LocalDate dietAppliedDate;
    private double dailyTotalCalorie;
    //@TODO 제거하고 각각 가져오는 것으로 변경 예정
    private Map<TypeOfTime, Double> groupedDietTotalCalorie;

    public static DailyOutputDto of(LocalDate localDate, double totalCalorie, Map<TypeOfTime, Double> groupedDietTotalCalorie) {
        return DailyOutputDto.builder()
                .dietAppliedDate(localDate)
                .dailyTotalCalorie(totalCalorie)
                .groupedDietTotalCalorie(groupedDietTotalCalorie)
                .build();
    }
}
