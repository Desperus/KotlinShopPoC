package com.epam.shopping

import com.epam.shopping.config.TestKafkaConfig
import com.epam.shopping.config.TestWebConfig
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(classes = [TestKafkaConfig::class, TestWebConfig::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
abstract class BaseIntegrationTest