package com.yoons.managediet.diet.controller

import com.yoons.managediet.diet.dto.DailyOutputDto
import com.yoons.managediet.diet.dto.DietSaveInputDto
import com.yoons.managediet.diet.dto.DietOutputDto
import com.yoons.managediet.diet.entity.TypeOfTime
import com.yoons.managediet.diet.service.AnalyzeDailyDietService
import groovy.util.logging.Slf4j
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import spock.lang.Specification

import java.time.LocalDate

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Slf4j
class DietControllerTest extends Specification {

    private MockMvc mockMvc
    private AnalyzeDailyDietService analyzeDailyDietService = Mock()

    def "LocalDateTime mapping 테스트"() {
        given:
        String content = '{ "totalCalorie": 250.9, "recipeList": [16,17,18], "dietAppliedDate": "2023-08-17", "typeOfTime": 1 }'

        mockMvc = MockMvcBuilders
                .standaloneSetup(new DietController(analyzeDailyDietService))
                .build()

        def dietInputDto = DietSaveInputDto.builder()
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

    def "/diet/daily 전체 식단 조회 테스트"() {
        given:
        def dailyOutputDto = DailyOutputDto.builder().build()
        def param = LocalDate.now()

        mockMvc = MockMvcBuilders
                .standaloneSetup(new DietController(analyzeDailyDietService))
                .build()

        when:
        def resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/diet/daily")
                        .queryParam("date", param.toString())
        )

        then:
        1 * analyzeDailyDietService.getOneDayDiet(argument -> {
            assert argument == param
        }) >> dailyOutputDto
        resultActions.andExpect(status().isOk())
    }

    def "GET /diet/daily/time 타임별 식단 조회 테스트"() {
        given:
        def dietOutputDto = DietOutputDto.builder().build()
        def date = LocalDate.now()
        def typeOfTime = TypeOfTime.MORNING

        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>()
        paramsMap.add("date", date.toString())
        paramsMap.add("time", typeOfTime.name())

        mockMvc = MockMvcBuilders
                .standaloneSetup(new DietController(analyzeDailyDietService))
                .build()

        when:
        def resultActions = mockMvc.perform(
                MockMvcRequestBuilders.get("/diet/daily/time")
                        .queryParams(paramsMap)
        )

        then:
        1 * analyzeDailyDietService.getPartDiet(
                {argument1 -> assert argument1 == date},
                {argument2 -> assert argument2 == typeOfTime}
        ) >> dietOutputDto
        resultActions.andExpect(status().isOk())
    }
}
