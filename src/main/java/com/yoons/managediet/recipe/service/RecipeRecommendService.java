package com.yoons.managediet.recipe.service;

import com.yoons.managediet.recipe.dto.CalorieOutputDto;
import com.yoons.managediet.recipe.dto.RecipeDto;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeRecommendService {

    private final RecipeRepositoryService recipeRepositoryService;

    public CalorieOutputDto<RecipeDto> recommendRecipeList(double calorie) {
        List<Recipe> recipeList = recipeRepositoryService.getRecipeByMaxCalorie(calorie);
        return getRecommendRecipeList(recipeList);
    }

    public CalorieOutputDto<RecipeDto> recommendRecipeList(double calorie, Long type) {
        List<Recipe> recipeList = recipeRepositoryService.getRecipeByMaxCalorieAndType(calorie, type);
        return getRecommendRecipeList(recipeList);
    }

    public CalorieOutputDto<RecipeDto> getRecommendRecipeList(List<Recipe> recipeList) {
        //@Todo recipeList 가 null일 때 exception 처리?
        List<RecipeDto> recipeDtoList = recipeList.stream()
                .map(RecipeDto::from)
                .sorted(Comparator.comparing(RecipeDto::getCalorie).reversed())
                .collect(Collectors.toList());
        return new CalorieOutputDto<>(recipeDtoList.size(), recipeDtoList);
    }
}
