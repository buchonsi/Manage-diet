package com.yoons.managediet.recipe.entity;

import com.yoons.managediet.BaseTimeEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "recipe")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recipe extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String recipeName;
    private double calorie;
    private double carbohydrate;        //탄수화물
    private double protein;             //단백질
    private double fat;                 //지방
    private double sodium;              //나트륨
    private String image;            //이미지(대)

    @ManyToOne
    @JoinColumn(name = "type")
    private RecipeType recipeType;
}
