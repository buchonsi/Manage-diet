package com.yoons.managediet.recipe.service;

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

    public List<RecipeDto> recommendRecipeList(double calorie) {
        return recipeRepositoryService.getRecipeByMaxCalorie(calorie).stream()
                .map(recipe -> convertToRecipeDto(recipe))
                .sorted(Comparator.comparing(RecipeDto::getCalorie).reversed())
                .collect(Collectors.toList())
                ;
    }

    private RecipeDto convertToRecipeDto(Recipe recipe) {
        return RecipeDto.builder()
                .id(recipe.getId())
                .recipeName(recipe.getRecipeName())
                .calorie(recipe.getCalorie())
                .carbohydrate(recipe.getCarbohydrate())
                .protein(recipe.getProtein())
                .fat(recipe.getFat())
                .sodium(recipe.getSodium())
                .image(recipe.getImage())
                .build();
    }
}
