package com.yoons.managediet.recipe.controller;

import com.yoons.managediet.recipe.dto.InputDto;
import com.yoons.managediet.recipe.dto.RecipeDto;
import com.yoons.managediet.recipe.service.RecipeRecommendService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class RecipeRecommendController {

    private final RecipeRecommendService recipeRecommendService;

    //@Todo return 양식 통일 되도록 만들기
    @GetMapping("/recipe/search/calorie")
    public List<RecipeDto> getRecommendRecipeList(@RequestBody InputDto inputDto) {
        return recipeRecommendService.recommendRecipeList(inputDto.getCalorie());
    }
}
