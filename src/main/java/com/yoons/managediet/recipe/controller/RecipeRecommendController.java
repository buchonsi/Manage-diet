package com.yoons.managediet.recipe.controller;

import com.yoons.managediet.recipe.dto.InputDto;
import com.yoons.managediet.recipe.service.RecipeRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeRecommendController {

    private final RecipeRecommendService recipeRecommendService;

    @GetMapping("/recipe/search/calorie")
    public ResponseEntity<Object> getRecommendRecipeList(@RequestBody InputDto inputDto) {
        return ResponseEntity.ok(recipeRecommendService.recommendRecipeList(inputDto.getCalorie()));
    }
}
