package com.yoons.managediet.api.service

import com.yoons.managediet.api.dto.OpenApiParamDto
import com.yoons.managediet.api.dto.OpenApiResponseDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OpenApiRecipeSearchServiceTest extends Specification {

    @Autowired
    private OpenApiRecipeSearchService openApiRecipeSearchService

    def "Api get test(인덱스 5)"() {
        given:
        def openApiParamDto = OpenApiParamDto.builder()
                .recipeName("파스타")
                .build()
        when:
        def result = openApiRecipeSearchService.requestRecipeSearch(openApiParamDto)

        then:
        result.getRawDto().size() == 5
    }

    def "Api get test - raw 데이터 null일 때"() {
        given:
        def openApiParamDto = OpenApiParamDto.builder()
                .recipeName("이상한거")
                .build()
        when:
        def result = openApiRecipeSearchService.requestRecipeSearch(openApiParamDto)

        then:
        result == null
    }
}
