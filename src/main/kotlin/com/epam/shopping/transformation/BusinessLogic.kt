package com.epam.shopping.transformation

import com.epam.shopping.model.ProductChangeEvent
import com.epam.shopping.util.loggerFor
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@Component
class BusinessLogic(
        private val webClient: WebClient
) {

    private val log = loggerFor(BusinessLogic::class.java)

    fun processEvents(productEvents: List<ProductChangeEvent>) {
        productEvents.forEach {
            log.info("Received event {}", it)
            webClient.post()
                    .uri("/${it.id}")
                    .bodyValue(ProductChangeEvent::class.java)
                    .retrieve()
                    .toBodilessEntity()
                    .block(Duration.ofSeconds(10L))
        }
        log.info("Sent events to external system")
    }

}