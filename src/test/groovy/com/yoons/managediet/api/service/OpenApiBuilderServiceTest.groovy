package com.yoons.managediet.api.service

import com.yoons.managediet.api.dto.OpenApiParamDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OpenApiBuilderServiceTest extends Specification {

    @Autowired
    private OpenApiBuilderService openApiBuilderService

    def "Uri Builder Test - baseUrl"() {
        given:
        def startIdx = 1
        def endIdx = 5
        def openApiParamDto= null

        when:
        def uri = openApiBuilderService.buildUriByRecipeName(startIdx, endIdx, openApiParamDto)

        then:
        println uri.getRawPath()
    }

    def "Uri Builder Test - add params"() {
        given:
        def startIdx = 1
        def endIdx = 5
        def openApiParamDto= OpenApiParamDto.builder()
                .recipeName("파스타")
                .build()

        when:
        def uri = openApiBuilderService.buildUriByRecipeName(startIdx, endIdx, openApiParamDto)

        then:
        println uri.getRawPath()
    }
}
