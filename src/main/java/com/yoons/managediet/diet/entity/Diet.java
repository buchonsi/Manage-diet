package com.yoons.managediet.diet.entity;

import com.yoons.managediet.BaseTimeEntity;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity(name = "diet")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Diet extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private double targetCalorie;
    private double dietTotalCalorie;
    private double remainedCalorie;

//    @Enumerated(EnumType.STRING)
//    @Column(name = "time")
//    private TypeOfTime typeOfTime;

    @ManyToOne
    @JoinColumn(name = "morning_recipe")
    private Recipe morningRecipe;

    @ManyToOne
    @JoinColumn(name = "afternoon_recipe")
    private Recipe afternoonRecipe;

    @ManyToOne
    @JoinColumn(name = "night_recipe")
    private Recipe nightRecipe;

    private LocalDateTime dietAppliedDate;
}
