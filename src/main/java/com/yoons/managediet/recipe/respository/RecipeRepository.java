package com.yoons.managediet.recipe.respository;

import com.yoons.managediet.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
}
