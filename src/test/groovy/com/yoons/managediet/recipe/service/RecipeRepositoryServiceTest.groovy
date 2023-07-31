package com.yoons.managediet.recipe.service

import com.yoons.managediet.recipe.entity.Recipe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class RecipeRepositoryServiceTest extends Specification {

    @Autowired
    RecipeRepositoryService recipeRepositoryService

    def setup() {
        recipeRepositoryService.deleteAll()
    }

    def "saveAll test"() {
        given:
        def recipe1 = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .build()
        def recipe2 = Recipe.builder()
                .recipeName("부추 콩가루 찜")
                .calorie(215)
                .carbohydrate(20)
                .protein(14)
                .fat(9)
                .sodium(240)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                .build()

        when:
        recipeRepositoryService.saveAll(Arrays.asList(recipe1, recipe2))
        def result = recipeRepositoryService.findAll()

        then:
        result.size() == 2
    }

    def "save test"() {
        given:
        def recipe = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .build()
        when:
        recipeRepositoryService.save(recipe)
        def result = recipeRepositoryService.findAll()

        then:
        result.size() == 1
        result.get(0).getRecipeName() == "새우 두부 계란찜"
    }

    def "findByRecipeName test"() {
        given:
        def recipe = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .build()
        recipeRepositoryService.save(recipe)

        when:
        def result = recipeRepositoryService.findByRecipeName(recipeName)

        then:
        result.size() == exceptResult

        where:
        recipeName             |       exceptResult
        "새우 두부 계란찜"     |       1
        "로제 파스타"          |       0
    }

    def "deleteAll test"() {
        given:
        def recipe1 = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .build()
        def recipe2 = Recipe.builder()
                .recipeName("부추 콩가루 찜")
                .calorie(215)
                .carbohydrate(20)
                .protein(14)
                .fat(9)
                .sodium(240)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                .build()

        recipeRepositoryService.saveAll(Arrays.asList(recipe1, recipe2))

        when:
        recipeRepositoryService.deleteAll()
        def result = recipeRepositoryService.findAll()

        then:
        result.size() == 0
    }

    def "deleteById test"() {
        given:
        def recipe = Recipe.builder()
                .recipeName("새우 두부 계란찜")
                .calorie(220)
                .carbohydrate(3)
                .protein(14)
                .fat(17)
                .sodium(99)
                .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                .build()
        def savedRecipe = recipeRepositoryService.save(recipe)

        when:
        recipeRepositoryService.deleteById(savedRecipe.getId())
        def result = recipeRepositoryService.findAll()

        then:
        result.size() == 0
    }
}
