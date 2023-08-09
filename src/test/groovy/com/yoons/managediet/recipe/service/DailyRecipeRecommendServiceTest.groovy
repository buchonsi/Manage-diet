package com.yoons.managediet.recipe.service

import com.yoons.managediet.recipe.dto.DailyInputDto
import com.yoons.managediet.recipe.dto.RecipeDto
import com.yoons.managediet.recipe.entity.Recipe
import com.yoons.managediet.recipe.entity.RecipeType
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class DailyRecipeRecommendServiceTest extends Specification {

    @Autowired
    private DailyRecipeRecommendService dailyRecipeRecommendService
    @Autowired
    private RecipeTypeRepositoryService recipeTypeRepositoryService
    @Autowired
    private RecipeRepositoryService recipeRepositoryService

//    def setup() {
//        recipeRepositoryService.deleteAll()
//
//        List<RecipeType> recipeTypeList = new ArrayList<>()
//        recipeTypeList.addAll(
//                RecipeType.builder().typeName("반찬").build(),
//                RecipeType.builder().typeName("국").build()
//        )
//
//        recipeTypeRepositoryService.saveAll(recipeTypeList)
//        def savedRecipeTypeList = recipeTypeRepositoryService.findAll()
//
//        def recipe1 = Recipe.builder()
//                .recipeName("새우 두부 계란찜")
//                .calorie(220)
//                .carbohydrate(3)
//                .protein(14)
//                .fat(17)
//                .sodium(99)
//                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
//                .recipeType(savedRecipeTypeList.get(0))
//                .build()
//        def recipe2 = Recipe.builder()
//                .recipeName("부추 콩가루 찜")
//                .calorie(215)
//                .carbohydrate(20)
//                .protein(14)
//                .fat(9)
//                .sodium(240)
//                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
//                .recipeType(savedRecipeTypeList.get(0))
//                .build()
//        def recipe3 = Recipe.builder()
//                .recipeName("우렁된장국")
//                .calorie(110.3)
//                .carbohydrate(43)
//                .protein(22.3)
//                .fat(7.2)
//                .sodium(976)
//                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00636_1.png")
//                .recipeType(savedRecipeTypeList.get(1))
//                .build()
//        recipeRepositoryService.saveAll(Arrays.asList(recipe1, recipe2, recipe3))
//    }

    def "DailyRecipeRecommendService - 선택된 값 중복 처리 테스트"() {
        given:
        initRecipeData()

        def recipeList = recipeRepositoryService.findAll()
        DailyInputDto dailyInputDto = DailyInputDto.builder()
                .calorie(400)
                .morningRecipeId(morningRecipeId)
                .afternoonRecipeId(afternoonRecipeId)
                .nightRecipeId(nightRecipeId)
                .build()

        when:
        def result = dailyRecipeRecommendService.getRecipeListByBasicCalorie(dailyInputDto)

        then:
        result.size() == exceptedResult

        where:
        morningRecipeId | afternoonRecipeId | nightRecipeId | exceptedResult
        0               |   0               |  0            | 3
        1L              |   null            |  null         | 2
        1L              |   2L              |  null         | 1
        1L              |   2L              |  3L           | 0
    }

    private void initRecipeData() {
        List<RecipeType> recipeTypeList = new ArrayList<>()
        recipeTypeList.addAll(
                RecipeType.builder().typeName("반찬").build(),
                RecipeType.builder().typeName("국").build()
        )

        recipeTypeRepositoryService.saveAll(recipeTypeList)
        def savedRecipeTypeList = recipeTypeRepositoryService.findAll()

        def recipe1 = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .recipeType(savedRecipeTypeList.get(0))
                .build()
        def recipe2 = Recipe.builder()
                .recipeName("부추 콩가루 찜")
                .calorie(215)
                .carbohydrate(20)
                .protein(14)
                .fat(9)
                .sodium(240)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                .recipeType(savedRecipeTypeList.get(0))
                .build()
        def recipe3 = Recipe.builder()
                .recipeName("우렁된장국")
                .calorie(110.3)
                .carbohydrate(43)
                .protein(22.3)
                .fat(7.2)
                .sodium(976)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00636_1.png")
                .recipeType(savedRecipeTypeList.get(1))
                .build()
        recipeRepositoryService.saveAll(Arrays.asList(recipe1, recipe2, recipe3))
    }
}
