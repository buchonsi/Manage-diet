package com.yoons.managediet.diet.service;

import com.yoons.managediet.diet.dto.DailyInputDto;
import com.yoons.managediet.diet.dto.DailyOutputDto;
import com.yoons.managediet.diet.entity.Diet;
import com.yoons.managediet.diet.entity.TypeOfTime;
import com.yoons.managediet.diet.repository.DietRepository;
import com.yoons.managediet.recipe.dto.RecipeDto;
import com.yoons.managediet.recipe.entity.Recipe;
import com.yoons.managediet.recipe.service.RecipeRecommendService;
import com.yoons.managediet.recipe.service.RecipeRepositoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnalyzeDailyDietService {
    private final RecipeRecommendService recipeRecommendService;
    private final RecipeRepositoryService recipeRepositoryService;
    private final DietRepository dietRepository;

    @Transactional
    public Diet saveAll(DailyInputDto dailyInputDto) {
        //validate
        if (validateDailyDiet(dailyInputDto)) {
            //exception?
        }
        //recipe 가져와서
        return dietRepository.save(convertToDiet(dailyInputDto));
    }

    private Map<String, Recipe> getDailyRecipes(DailyInputDto dailyInputDto) {
        Map<String, Recipe> recipeMap = new HashMap<>();
        recipeMap.put(TypeOfTime.MORNING.name(), recipeRepositoryService.findById(dailyInputDto.getMorningRecipeId()));
        recipeMap.put(TypeOfTime.AFTERNOON.name(), recipeRepositoryService.findById(dailyInputDto.getAfternoonRecipeId()));
        recipeMap.put(TypeOfTime.NIGHT.name(), recipeRepositoryService.findById(dailyInputDto.getNightRecipeId()));
        return recipeMap;
    }

    private boolean validateDailyDiet(DailyInputDto dailyInputDto) {
        return Objects.isNull(dailyInputDto.getMorningRecipeId()) ||
                Objects.isNull(dailyInputDto.getAfternoonRecipeId()) ||
                Objects.isNull(dailyInputDto.getNightRecipeId());
    }

    private Diet convertToDiet(DailyInputDto dailyInputDto) {
        Recipe morningRecipe = recipeRepositoryService.findById(dailyInputDto.getMorningRecipeId());
        Recipe afternoonRecipe = recipeRepositoryService.findById(dailyInputDto.getAfternoonRecipeId());
        Recipe nightRecipe = recipeRepositoryService.findById(dailyInputDto.getNightRecipeId());


        double totalCalorie = morningRecipe.getCalorie() + afternoonRecipe.getCalorie() + nightRecipe.getCalorie();

        return Diet.builder()
                .totalCalorie(totalCalorie)
                .morningRecipe(morningRecipe)
                .afternoonRecipe(afternoonRecipe)
                .nightRecipe(nightRecipe)
                .dietAppliedDate(dailyInputDto.getDietAppliedDate())
                .build();
    }

    private double subtractCalorie(double basicCalorie, double checkedRecipeCalorie) {
        int basicValue = (int) (basicCalorie * 100);
        int checkedValue = (int) (checkedRecipeCalorie * 100);
        int result = basicValue - checkedValue;
        return (double) result / 100;
    }
}
