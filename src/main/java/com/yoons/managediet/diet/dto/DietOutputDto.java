package com.yoons.managediet.diet.dto;

import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class DietOutputDto {
    private TypeOfTime typeOfTime;
    private LocalDate dietAppliedDate;
    private List<Recipe> recipeList;

    private double dietTotalCalorie;

    //@TODO 코드 개선 필요
    public static DietOutputDto of(LocalDate localDate, TypeOfTime typeOfTime, DietRecipeOutputDto dietRecipeOutputDto) {
//        if (dietList == null || dietList.isEmpty()) {
//            throw new IllegalArgumentException("식단이 없습니다. 식단 설정을 해주세요.");
//        }
//
//        List<Recipe> recipeList = new ArrayList<>();
//        BigDecimal totalCalorie = BigDecimal.ZERO;
//
//        for (Diet diet : dietList) {
//            if (Objects.isNull(diet.getRecipe())) {
//                continue;
//            }
//            recipeList.add(diet.getRecipe());
//            BigDecimal calorie = BigDecimal.valueOf(diet.getRecipe().getCalorie());
//            totalCalorie = totalCalorie.add(calorie);
//        }

        return DietOutputDto.builder()
                .dietAppliedDate(localDate)
                .typeOfTime(typeOfTime)
                .recipeList(dietRecipeOutputDto.getRecipeList())
                .dietTotalCalorie(dietRecipeOutputDto.getDietTotalCalorie())
                .build();
    }
}
