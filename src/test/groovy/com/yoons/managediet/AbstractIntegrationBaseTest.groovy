package com.yoons.managediet


import org.springframework.boot.test.context.SpringBootTest
import spock.lang.Specification

import javax.transaction.Transactional

@SpringBootTest
@Transactional
abstract class AbstractIntegrationBaseTest extends Specification{
}
