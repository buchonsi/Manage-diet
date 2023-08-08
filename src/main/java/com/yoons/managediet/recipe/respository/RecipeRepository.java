package com.yoons.managediet.recipe.respository;

import com.yoons.managediet.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {

    List<Recipe> findByRecipeNameContaining(String recipeName);
    List<Recipe> findByCalorieLessThanEqual(double calorie);

    @Query(value = "select * from recipe where calorie <= ?1 and type = ?2", nativeQuery = true)
    List<Recipe> findByCalorieAndTypeId(double calorie, Long recipeTypeId);
}
