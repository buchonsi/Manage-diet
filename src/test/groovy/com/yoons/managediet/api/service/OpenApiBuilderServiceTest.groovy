package com.yoons.managediet.api.service

import com.yoons.managediet.api.dto.OpenApiParamDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

@SpringBootTest
class OpenApiBuilderServiceTest extends Specification {

    @Autowired
    private OpenApiBuilderService openApiBuilderService

    def "Uri Builder Test"() {
        given:
        def openApiParamDto= OpenApiParamDto.builder()
                .rct_nm("파스타")
                .build()

        when:
        def uri = openApiBuilderService.buildUriByRecipeName(openApiParamDto)

        then:
        println uri.getRawPath()
    }
}
