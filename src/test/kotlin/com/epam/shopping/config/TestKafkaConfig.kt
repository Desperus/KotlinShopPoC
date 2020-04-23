package com.epam.shopping.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.kafka.test.EmbeddedKafkaBroker

@TestConfiguration
class TestKafkaConfig {

    @Value("\${pocapp.topic.product}")
    private lateinit var productTopic: String

    @Bean
    fun kafkaBroker(): EmbeddedKafkaBroker {
        return EmbeddedKafkaBroker(1, true, 2, productTopic)
                .brokerListProperty("pocapp.test.bootstrap-servers")
    }

}