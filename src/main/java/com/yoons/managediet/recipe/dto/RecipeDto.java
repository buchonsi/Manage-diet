package com.yoons.managediet.recipe.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class RecipeDto {
    private Long id;
    private String recipeName;
    private double calorie;
    private double carbohydrate;        //탄수화물
    private double protein;             //단백질
    private double fat;                 //지방
    private double sodium;              //나트륨
    private String image;               //이미지(대)
    private String type;                //레시피 타입
}
