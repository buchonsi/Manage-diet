package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.TypeOfTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class DietSearchInputDto {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private TypeOfTime time;
}
