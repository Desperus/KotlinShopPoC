package com.epam.shopping.kafka

import com.epam.shopping.model.ProductChangeEvent
import com.epam.shopping.transformation.BusinessLogic
import com.epam.shopping.util.loggerFor
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductChangeEventConsumer(
        private val businessLogic: BusinessLogic
) {

    private val log = loggerFor(ProductChangeEventConsumer::class.java)

    @KafkaListener(topics = ["\${pocapp.topic.product}"])
    fun consumeProductEvents(productEvents: List<ProductChangeEvent>) {
        try {
            businessLogic.processEvents(productEvents)
        } catch (e: Exception) {
            log.error("Handle failover scenario", e)
        }
    }

}