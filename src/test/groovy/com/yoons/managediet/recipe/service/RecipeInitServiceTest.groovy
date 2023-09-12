package com.yoons.managediet.recipe.service

import com.yoons.managediet.AbstractIntegrationBaseTest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class RecipeInitServiceTest extends AbstractIntegrationBaseTest {

    @Autowired
    RecipeInitService recipeInitService

    def "init Database save test"() {
        when:
        def result = recipeInitService.initDatabase()

        then:
        result > 0
    }
}
