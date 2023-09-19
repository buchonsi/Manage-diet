package com.yoons.managediet.diet.controller

import com.yoons.managediet.diet.dto.DietInputDto
import com.yoons.managediet.diet.dto.DietOutputDto
import com.yoons.managediet.diet.entity.TypeOfTime
import com.yoons.managediet.diet.service.AnalyzeDailyDietService
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

//@ContextConfiguration(classes = ObjectMapperConfig.class)
class DietControllerTest extends Specification {

    private MockMvc mockMvc
    private AnalyzeDailyDietService analyzeDailyDietService = Mock()

    def "LocalDateTime mapping 테스트"() {
        given:
        String content = '{ "totalCalorie": 250.9, "recipeList": [16,17,18], "dietAppliedDate": "2023-08-17", "typeOfTime": 1 }'

        mockMvc = MockMvcBuilders
                .standaloneSetup(new DietController(analyzeDailyDietService))
                .build()

        def dietInputDto = DietInputDto.builder()
                .typeOfTime(TypeOfTime.MORNING)
                .totalCalorie(250.9)
                .dietAppliedDate(LocalDate.now())
                .build()

        when:
        def resultActions = mockMvc.perform(
                MockMvcRequestBuilders.post("/daily/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content)
        )

        then:
        analyzeDailyDietService.saveDiet(dietInputDto) >> DietOutputDto
        resultActions.andExpect(status().isOk())
    }
}
