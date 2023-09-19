package com.yoons.managediet.diet.entity;

import com.yoons.managediet.recipe.entity.Recipe;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "diet", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "time",
                "applied_date",
                "recipe_id"
        })
})
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
    @Column(name = "applied_date")
    private LocalDate dietAppliedDate;

    @ManyToOne
    private Recipe recipe;
}
