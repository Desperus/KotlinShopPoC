package com.epam.shopping.kafka

import com.epam.shopping.model.ProductChangeEvent
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.springframework.kafka.annotation.KafkaListener

class ProductChangeEventConsumer {

    @KafkaListener(topics = ["\${pocapp.topic.product}"])
    fun consumeProductEvents(productRecords: List<ConsumerRecord<String, ProductChangeEvent>>) {
        try {
            productRecords.forEach { println("Received event [key=${it.key()}, value=${it.value()}") }
        } catch (e: Exception) {
            println("Handle failover scenario")
        }
    }

}