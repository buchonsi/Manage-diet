package com.yoons.managediet.recipe.service;

import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.respository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

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

    public Recipe save(Recipe recipe) {
        if (Objects.isNull(recipe)) {
            return null;
        }
        return recipeRepository.save(recipe);
    }

    @Transactional(readOnly = true)
    public List<Recipe> findAll() {
        return recipeRepository.findAll();
    }

    @Transactional(readOnly = true)
    public List<Recipe> findByRecipeName(String recipeName) {
        return recipeRepository.findByRecipeNameContaining(recipeName);
    }

    public void deleteAll() {
        recipeRepository.deleteAll();
    }

    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }
}
