package com.yoons.managediet.recipe.controller;

import com.yoons.managediet.recipe.service.RecipeInitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecipeDatabaseInitController {

    private final RecipeInitService recipeInitService;

    @PostMapping("/initDatabase")
    public int initRecipeDatabase() {
        return recipeInitService.initDatabase();
    }
}
