package com.epam.shopping.kafka

import com.epam.shopping.model.ProductChangeEvent
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class ProductChangeEventConsumer {

    @KafkaListener(topics = ["\${pocapp.topic.product}"])
    fun consumeProductEvents(productEvents: List<ProductChangeEvent>) {
        try {
            productEvents.forEach { println("Received event $it") }
        } catch (e: Exception) {
            println("Handle failover scenario")
        }
    }

}