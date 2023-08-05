package com.yoons.managediet.recipe.respository;

import com.yoons.managediet.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByRecipeNameContaining(String recipeName);
    List<Recipe> findByCalorieLessThanEqual(double calorie);
}
