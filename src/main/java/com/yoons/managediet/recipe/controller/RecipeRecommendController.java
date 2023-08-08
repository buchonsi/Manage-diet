package com.yoons.managediet.recipe.controller;

import com.yoons.managediet.recipe.service.RecipeRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeRecommendController {

    private final RecipeRecommendService recipeRecommendService;

    @GetMapping("/recipe/search/{calorie}")
    public ResponseEntity<Object> getRecommendRecipeList(@PathVariable("calorie") Long calorie) {
        return ResponseEntity.ok(recipeRecommendService.recommendRecipeList(calorie));
    }

    @GetMapping("/recipe/search/{calorie}/{typeId}")
    public ResponseEntity<Object> getRecipeByType(@PathVariable("calorie") double calorie, @PathVariable("typeId") Long typeId) {
        return ResponseEntity.ok(recipeRecommendService.recommendRecipeList(calorie, typeId));
    }
}
