package com.yoons.managediet.recipe.service;

import com.yoons.managediet.api.dto.OpenApiResponseDto;
import com.yoons.managediet.api.service.OpenApiRecipeSearchService;
import com.yoons.managediet.recipe.entity.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeInitService {

    private final OpenApiRecipeSearchService openApiRecipeSearchService;
    private final RecipeRepositoryService recipeRepositoryService;

    @Transactional
    public int initDatabase() {
        //@Todo 검증 로직 (db 데이터 사이즈 0 아니면 초기화) - delete작성
        List<Recipe> recipeList = recipeRepositoryService.saveAll(getAllRecipe());
        return recipeList.size();
    }

    private List<Recipe> getAllRecipe() {
        int startIdx = 1;
        int endIdx = 1000;
        int totalCnt = 0;
        List<Recipe> recipeList = new ArrayList<>();

        while (true) {
            OpenApiResponseDto openApiResponseDto = openApiRecipeSearchService.requestRecipeSearch(startIdx, endIdx, null);

            if (Objects.isNull(openApiResponseDto)) {

                break;
            }

            recipeList.addAll(
                    openApiResponseDto.getRawDto().stream()
                            .map(rawDto -> Recipe.builder()
                                    .recipeName(rawDto.getRecipeName())
                                    .build())
                            .collect(Collectors.toList()));
            startIdx += 1000;
            endIdx += 1000;
            totalCnt = openApiResponseDto.getTotalCnt();
        }

        log.info("[RecipeInitService getAllRecipe] total count : {}", totalCnt);
        return recipeList;
    }
}
