package com.epam.shopping.transformation

import com.epam.shopping.model.ProductChangeEvent
import com.epam.shopping.util.loggerFor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@Component
class BusinessLogic(
        @Qualifier("externalWebClient") private val webClient: WebClient
) {

    private val log = loggerFor(BusinessLogic::class.java)

    fun processEvents(productEvents: List<ProductChangeEvent>) {
        productEvents.forEach {
            log.info("Received event {}", it)
            webClient.post()
                    .uri("/shop/product/${it.id}")
                    .bodyValue(it)
                    .retrieve()
                    .toBodilessEntity()
                    .block(Duration.ofSeconds(10L))
        }
        log.info("Sent all events to external system")
    }

}