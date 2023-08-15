package com.yoons.managediet.diet.entity;

public enum TypeOfTime {
    MORNING("아침"),
    AFTERNOON("점심"),
    NIGHT("저녁");

    private final String time;

    TypeOfTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
