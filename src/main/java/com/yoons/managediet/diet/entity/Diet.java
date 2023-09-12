package com.yoons.managediet.diet.entity;

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
public class Diet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "time")
    private TypeOfTime typeOfTime;

    private LocalDateTime dietAppliedDate;

    @ManyToOne
    private Recipe recipe;
}
