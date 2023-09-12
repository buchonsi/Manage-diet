package com.yoons.managediet.diet.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TypeOfTime {
    MORNING(1, "아침"),
    AFTERNOON(2, "점심"),
    NIGHT(3,"저녁");

    private final int id;
    private final String time;
}
