package com.epam.shopping.kafka

import org.springframework.kafka.annotation.KafkaListener

class ProductChangeEventConsumer {

    @KafkaListener(topics = ["#{pocapp.topics}"])
    fun consumeProductEvents() {

    }

}