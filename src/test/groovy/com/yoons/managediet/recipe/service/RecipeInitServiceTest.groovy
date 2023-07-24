package com.yoons.managediet.recipe.service


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import spock.lang.Specification

@SpringBootTest
//@ActiveProfiles("common")
class RecipeInitServiceTest extends Specification {

    @Autowired
    RecipeInitService recipeInitService

    def "init Database save test"() {
        when:
        def result = recipeInitService.initDatabase()

        then:
        result > 0
    }
}
