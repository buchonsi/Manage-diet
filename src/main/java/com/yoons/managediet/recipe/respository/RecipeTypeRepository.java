package com.yoons.managediet.recipe.respository;

import com.yoons.managediet.recipe.entity.RecipeType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeTypeRepository extends JpaRepository<RecipeType, Long> {

    Optional<RecipeType> findByTypeName(String typeName);
}
