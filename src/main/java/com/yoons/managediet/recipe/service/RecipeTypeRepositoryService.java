package com.yoons.managediet.recipe.service;

import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.entity.RecipeType;
import com.yoons.managediet.recipe.respository.RecipeTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeTypeRepositoryService {

    private final RecipeTypeRepository recipeTypeRepository;

    public List<RecipeType> saveAll(List<RecipeType> recipeType) {
        return recipeTypeRepository.saveAll(recipeType);
    }

    public RecipeType save(RecipeType recipeType) {
        return recipeTypeRepository.save(recipeType);
    }

    public List<RecipeType> findAll() {
        return recipeTypeRepository.findAll();
    }

    public Optional<RecipeType> findByTypeName(String typeName) {
        return recipeTypeRepository.findByTypeName(typeName);
    }
}
