package com.yoons.managediet.recipe.service


import com.yoons.managediet.recipe.entity.Recipe
import spock.lang.Specification

class RecipeRecommendServiceTest extends Specification {


    private RecipeRepositoryService recipeRepositoryService = Mock()
    private RecipeRecommendService recipeRecommendService = new RecipeRecommendService(recipeRepositoryService)
    private List<Recipe> recipeList

    def setup() {
        recipeList = new ArrayList<>()
        recipeList.addAll(
                Recipe.builder()
                        .recipeName("새우 두부 계란찜")
                        .calorie(215)
                        .carbohydrate(3)
                        .protein(14)
                        .fat(17)
                        .sodium(99)
                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                        .build(),
                Recipe.builder()
                        .recipeName("부추 콩가루 찜")
                        .calorie(220)
                        .carbohydrate(20)
                        .protein(14)
                        .fat(9)
                        .sodium(240)
                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                        .build(),
                Recipe.builder()
                        .recipeName("우렁된장소스 배추롤")
                        .calorie(110.3)
                        .carbohydrate(43)
                        .protein(22.3)
                        .fat(7.2)
                        .sodium(976)
                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00636_1.png")
                        .build()
        )
    }

    def "recommendRecipeList - 결과 값이 정렬 조회되는지 확인"() {
        given:
        def calorie = 220.0

        when:
        recipeRepositoryService.getRecipeByMaxCalorie(calorie) >> recipeList
        def result = recipeRecommendService.recommendRecipeList(calorie)

        then:
        result.get(0).getCalorie() > result.get(1).getCalorie()
        result.get(0).getCalorie() > result.get(2).getCalorie()
        result.get(1).getCalorie() > result.get(2).getCalorie()
    }
}