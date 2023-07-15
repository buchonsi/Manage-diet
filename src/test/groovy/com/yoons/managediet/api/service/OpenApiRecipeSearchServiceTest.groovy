package com.yoons.managediet.api.service

import com.yoons.managediet.api.dto.OpenApiParamDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OpenApiRecipeSearchServiceTest extends Specification {

    @Autowired
    private OpenApiRecipeSearchService openApiRecipeSearchService

    def "Api get test"() {
        given:
        def openApiParamDto = OpenApiParamDto.builder()
                .recipeName("파스타")
                .build()
        when:
        openApiRecipeSearchService.requestRecipeSearch(openApiParamDto)

        then:
        println "ok"
    }
}
