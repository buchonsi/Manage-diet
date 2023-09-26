package com.yoons.managediet.diet.service

import com.yoons.managediet.AbstractIntegrationBaseTest
import com.yoons.managediet.diet.entity.Diet
import com.yoons.managediet.diet.entity.TypeOfTime
import com.yoons.managediet.recipe.entity.Recipe
import com.yoons.managediet.recipe.entity.RecipeType
import com.yoons.managediet.recipe.service.RecipeRepositoryService
import com.yoons.managediet.recipe.service.RecipeTypeRepositoryService
import org.springframework.beans.factory.annotation.Autowired

import java.time.LocalDate

class DietRepositoryServiceTest extends AbstractIntegrationBaseTest {

    @Autowired
    RecipeRepositoryService recipeRepositoryService

    @Autowired
    RecipeTypeRepositoryService recipeTypeRepositoryService

    @Autowired
    DietRepositoryService dietRepositoryService

    List<Recipe> savedRecipeList

    def setup() {
        def savedRecipeTypeList = recipeTypeRepositoryService.saveAll(
                Arrays.asList(
                        RecipeType.builder().typeName("반찬").build(),
                        RecipeType.builder().typeName("국").build()
                )
        )
        savedRecipeList =
                recipeRepositoryService.saveAll(
                        Arrays.asList(
                                Recipe.builder()
                                        .recipeName("새우 두부 계란찜")
                                        .calorie(220)
                                        .carbohydrate(3)
                                        .protein(14)
                                        .fat(17)
                                        .sodium(99)
                                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                                        .recipeType(savedRecipeTypeList.get(0))
                                        .build(),
                                Recipe.builder()
                                        .recipeName("부추 콩가루 찜")
                                        .calorie(215)
                                        .carbohydrate(20)
                                        .protein(14)
                                        .fat(9)
                                        .sodium(240)
                                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                                        .recipeType(savedRecipeTypeList.get(0))
                                        .build(),
                                Recipe.builder()
                                        .recipeName("우렁된장국")
                                        .calorie(110.3)
                                        .carbohydrate(43)
                                        .protein(22.3)
                                        .fat(7.2)
                                        .sodium(976)
                                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00636_1.png")
                                        .recipeType(savedRecipeTypeList.get(1))
                                        .build()
                        )
                )
    }

    def "saveAll success Test"() {
        given:
        def diet1 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(0))
                .build()
        def diet2 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(1))
                .build()
        def diet3 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(2))
                .build()
        when:
        def savedDietList = dietRepositoryService.saveAll(Arrays.asList(diet1, diet2, diet3))

        then:
        savedRecipeList.size() > 0
        savedRecipeList.size() == 3
    }

    def "findByDietAppliedDate success test"() {
        given:
        def appliedDate = LocalDate.now()
        def diet1 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(0))
                .build()
        def diet2 = Diet.builder()
                .typeOfTime(TypeOfTime.NIGHT)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(1))
                .build()
        def diet3 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now().plusDays(1))       //day+
                .recipe(savedRecipeList.get(2))
                .build()

        dietRepositoryService.saveAll(Arrays.asList(diet1, diet2, diet3))

        when:
        def dietList = dietRepositoryService.findByDietAppliedDate(appliedDate)

        then:
        dietList.size() == 2
    }

    def "findByDietAppliedDateAndTypeOfTime success test"() {
        given:
        def appliedDate = LocalDate.now()
        def typeOfTime = TypeOfTime.MORNING

        def diet1 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(0))
                .build()
        def diet2 = Diet.builder()
                .typeOfTime(TypeOfTime.NIGHT)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(1))
                .build()
        def diet3 = Diet.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(2))
                .build()

        dietRepositoryService.saveAll(Arrays.asList(diet1, diet2, diet3))

        when:
        def dietList = dietRepositoryService.findByDietAppliedDateAndTypeOfTime(appliedDate, typeOfTime)

        then:
        dietList.size() == 2
    }
}
