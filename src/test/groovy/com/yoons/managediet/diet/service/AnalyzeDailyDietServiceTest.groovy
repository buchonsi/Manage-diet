package com.yoons.managediet.diet.service

import com.yoons.managediet.diet.dto.DailyInputDto
import com.yoons.managediet.diet.entity.Diet
import com.yoons.managediet.recipe.entity.Recipe
import com.yoons.managediet.recipe.entity.RecipeType
import com.yoons.managediet.recipe.service.RecipeRepositoryService
import com.yoons.managediet.recipe.service.RecipeTypeRepositoryService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class AnalyzeDailyDietServiceTest extends Specification {

    @Autowired
    AnalyzeDailyDietService analyzeDailyDietService

    @Autowired
    RecipeRepositoryService recipeRepositoryService
    @Autowired
    RecipeTypeRepositoryService recipeTypeRepositoryService

    DailyInputDto dailyInputDto


    def setup() {
        //Database clear
        deleteAll()

        //recipeType 초기화
        recipeTypeInit()

        //recipe 초기화
        recipeInit()
    }

    def "save - 정상 동작 테스트"() {
        given:
        def savedRecipeList = recipeRepositoryService.findAll()
        def recipe1 = savedRecipeList.get(0)
        def recipe2 = savedRecipeList.get(1)
        def recipe3 = savedRecipeList.get(2)

        DailyInputDto dailyInputDto = DailyInputDto.builder()
                .targetCalorie(2000)
                .morningRecipeId(recipe1.getId())
                .afternoonRecipeId(recipe2.getId())
                .nightRecipeId(recipe3.getId())
                .build()
        when:
        def result = analyzeDailyDietService.save(dailyInputDto)

        then:
        result.getDietTotalCalorie() == (recipe1.getCalorie() + recipe2.getCalorie() + recipe3.getCalorie())
        result.getRemainedCalorie() == (2000 - recipe1.getCalorie() - recipe2.getCalorie() - recipe3.getCalorie())
        result.getMorningRecipe().getRecipeName() == recipe1.getRecipeName()
        result.getAfternoonRecipe().getRecipeName() == recipe2.getRecipeName()
        result.getNightRecipe().getRecipeName() == recipe3.getRecipeName()
    }

    private void deleteAll() {
        recipeTypeRepositoryService.deleteAll()
        recipeRepositoryService.deleteAll()
    }

    private void recipeTypeInit() {
        def type1 = RecipeType.builder().id(1L).typeName("반찬").build()
        def type2 = RecipeType.builder().id(2L).typeName("국").build()

        recipeTypeRepositoryService.saveAll(Arrays.asList(type1, type2))
    }

    private void recipeInit() {
        def recipeTypeList = recipeTypeRepositoryService.findAll()

        def recipe1 = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .recipeType(recipeTypeList.get(0))
                .build()
        def recipe2 = Recipe.builder()
                .recipeName("부추 콩가루 찜")
                .calorie(215)
                .carbohydrate(20)
                .protein(14)
                .fat(9)
                .sodium(240)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                .recipeType(recipeTypeList.get(0))
                .build()
        def recipe3 = Recipe.builder()
                .recipeName("우렁된장국")
                .calorie(110.3)
                .carbohydrate(43)
                .protein(22.3)
                .fat(7.2)
                .sodium(976)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00636_1.png")
                .recipeType(recipeTypeList.get(1))
                .build()

        recipeRepositoryService.saveAll(Arrays.asList(recipe1, recipe2, recipe3))
    }
}
