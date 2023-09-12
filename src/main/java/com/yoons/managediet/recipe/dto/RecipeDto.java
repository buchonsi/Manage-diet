package com.yoons.managediet.recipe.dto;

import com.yoons.managediet.recipe.entity.Recipe;
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

    public static RecipeDto from(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .recipeName(recipe.getRecipeName())
                .calorie(recipe.getCalorie())
                .carbohydrate(recipe.getCarbohydrate())
                .protein(recipe.getProtein())
                .fat(recipe.getFat())
                .sodium(recipe.getSodium())
                .image(recipe.getImage())
                .type(recipe.getRecipeType().getTypeName())
                .build();
    }
}
