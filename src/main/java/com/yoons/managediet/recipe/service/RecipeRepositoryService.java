package com.yoons.managediet.recipe.service;

import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.respository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RecipeRepositoryService {

    private final RecipeRepository recipeRepository;

    public List<Recipe> saveAll(List<Recipe> recipeList) {
        if (CollectionUtils.isEmpty(recipeList)) {
            return Collections.emptyList();
        }
        return recipeRepository.saveAll(recipeList);
    }
}
