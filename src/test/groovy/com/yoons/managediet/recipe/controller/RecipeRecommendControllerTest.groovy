package com.yoons.managediet.recipe.controller

import com.yoons.managediet.recipe.dto.CalorieOutputDto
import com.yoons.managediet.recipe.dto.RecipeDto
import com.yoons.managediet.recipe.service.RecipeRecommendService
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.handler
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class RecipeRecommendControllerTest extends Specification {

    private MockMvc mockMvc
    private RecipeRecommendService recipeRecommendService = Mock()
    private CalorieOutputDto<RecipeDto> calorieOutputDto


    def setup() {
        mockMvc = MockMvcBuilders
                .standaloneSetup(new RecipeRecommendController(recipeRecommendService))
                .build()

        def recipeList = new ArrayList<>()
        recipeList.addAll(
                RecipeDto.builder()
                        .recipeName("새우 두부 계란찜")
                        .calorie(215)
                        .carbohydrate(3)
                        .protein(14)
                        .fat(17)
                        .sodium(99)
                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00028_1.png")
                        .build(),
                RecipeDto.builder()
                        .recipeName("부추 콩가루 찜")
                        .calorie(220)
                        .carbohydrate(20)
                        .protein(14)
                        .fat(9)
                        .sodium(240)
                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00029_1.png")
                        .build(),
                RecipeDto.builder()
                        .recipeName("우렁된장소스 배추롤")
                        .calorie(110.3)
                        .carbohydrate(43)
                        .protein(22.3)
                        .fat(7.2)
                        .sodium(976)
                        .image("http://www.foodsafetykorea.go.kr/uploadimg/cook/10_00636_1.png")
                        .build()
        )

        calorieOutputDto = CalorieOutputDto.builder()
                .totalCount(recipeList.size())
                .values(recipeList)
                .build()


    }

    def "Get /recipe/search/{calorie}"() {
        given:
        def calorie = 220

        when:
        def resultAction = mockMvc.perform(get("/recipe/search/" + calorie))

        then:
        1 * recipeRecommendService.recommendRecipeList(argument -> {
            assert argument == calorie
        }) >> calorieOutputDto

        resultAction.andExpect(status().isOk())
                .andExpect(handler().handlerType(RecipeRecommendController.class))
                .andExpect(handler().methodName("getRecommendRecipeList"))
                .andDo(print())
    }
}
