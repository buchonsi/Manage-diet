package com.yoons.managediet.diet.service

import com.yoons.managediet.AbstractIntegrationBaseTest
import com.yoons.managediet.diet.dto.DietSaveInputDto
import com.yoons.managediet.diet.entity.Diet
import com.yoons.managediet.diet.entity.TypeOfTime
import com.yoons.managediet.recipe.entity.Recipe
import com.yoons.managediet.recipe.entity.RecipeType
import com.yoons.managediet.recipe.service.RecipeRepositoryService
import com.yoons.managediet.recipe.service.RecipeTypeRepositoryService
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

import java.time.LocalDate

@SpringBootTest
@Slf4j
class AnalyzeDailyDietServiceTest extends AbstractIntegrationBaseTest {

    @Autowired
    AnalyzeDailyDietService analyzeDailyDietService
    @Autowired
    DietRepositoryService dietRepositoryService

    @Autowired
    RecipeRepositoryService recipeRepositoryService
    @Autowired
    RecipeTypeRepositoryService recipeTypeRepositoryService

    def setup() {
        //Database clear
//        deleteAll()

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
        def dateTime = LocalDate.now()

        DietSaveInputDto dietSaveInputDto = DietSaveInputDto.builder()
                .totalCalorie(545.3)
                .dietAppliedDate(dateTime)
                .recipeList(Arrays.asList(recipe1.getId(), recipe2.getId(), recipe3.getId()))
                .typeOfTime(TypeOfTime.MORNING)
                .build()

        when:
        def result = analyzeDailyDietService.saveDiet(dietSaveInputDto)

        then:
        result.getDietTotalCalorie() == (recipe1.getCalorie() + recipe2.getCalorie() + recipe3.getCalorie())
        result.getDietAppliedDate() == dateTime
        result.getRecipeList().size() == 3
        result.getTypeOfTime() == TypeOfTime.MORNING
    }

    def "classifyTime() 동작 테스트 - TypeOfTime 으로 grouping 테스트"() {
        given:
        def appliedDate = LocalDate.now()
        def typeOfTime = TypeOfTime.MORNING
        def savedRecipeList = recipeRepositoryService.findAll()

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
        def diet4 = Diet.builder()
                .typeOfTime(TypeOfTime.AFTERNOON)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(2))
                .build()
        def dietList = List.of(diet1, diet2, diet3, diet4)

        when:
        def dietMap = analyzeDailyDietService.classifyTime(dietList)
        then:
        dietMap.get(TypeOfTime.MORNING).size() == 2
        dietMap.get(TypeOfTime.AFTERNOON).size() == 1
        dietMap.get(TypeOfTime.NIGHT).size() == 1
    }

    def "getOneDayDiet() 동작 테스트 - 전체 결과 조회"() {
        given:
        def appliedDate = LocalDate.now()
        def typeOfTime = TypeOfTime.MORNING
        def savedRecipeList = recipeRepositoryService.findAll()

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
        def diet4 = Diet.builder()
                .typeOfTime(TypeOfTime.AFTERNOON)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(2))
                .build()
        def dietList = List.of(diet1, diet2, diet3, diet4)
        dietRepositoryService.saveAll(dietList)

        when:
        def dailyOutputDto = analyzeDailyDietService.getOneDayDiet(LocalDate.now())

        then:
        dailyOutputDto.getDietAppliedDate() == appliedDate
        dailyOutputDto.getGroupedDietTotalCalorie().size() == 3
        log.info("getOneDayDiet() result : {}", dailyOutputDto)
    }

    def "getPartDiet() 동작 테스트"() {
        given:
        def appliedDate = LocalDate.now()
        def typeOfTime = TypeOfTime.MORNING
        def savedRecipeList = recipeRepositoryService.findAll()

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
        def diet4 = Diet.builder()
                .typeOfTime(TypeOfTime.AFTERNOON)
                .dietAppliedDate(LocalDate.now())
                .recipe(savedRecipeList.get(2))
                .build()
        def dietList = List.of(diet1, diet2, diet3, diet4)
        dietRepositoryService.saveAll(dietList)

        when:
        def dietOutputDto = analyzeDailyDietService.getPartDiet(appliedDate, typeOfTime)

        then:
        log.info("getPartDiet() result : {}", dietOutputDto)
    }
//    private void deleteAll() {
//        recipeTypeRepositoryService.deleteAll()
//        recipeRepositoryService.deleteAll()
//    }

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
