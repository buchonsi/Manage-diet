package com.yoons.managediet.recipe.service;

import com.yoons.managediet.recipe.dto.DailyInputDto;
import com.yoons.managediet.recipe.dto.RecipeDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DailyRecipeRecommendService {
    private final RecipeRecommendService recipeRecommendService;

    public List<RecipeDto> getRecipeListByBasicCalorie(DailyInputDto dailyInputDto) {
        return recipeRecommendService.recommendRecipeList(dailyInputDto.getCalorie()).getValues()
                .stream().filter(recipeDto -> CheckDuplicationRecipe(recipeDto.getId(), dailyInputDto))
                .collect(Collectors.toList());
    }

    private boolean CheckDuplicationRecipe(Long recipeId, DailyInputDto inputDto) {
        return !Objects.equals(recipeId, inputDto.getMorningRecipeId()) &&
                !Objects.equals(recipeId, inputDto.getAfternoonRecipeId()) &&
                !Objects.equals(recipeId, inputDto.getNightRecipeId());
    }
}
