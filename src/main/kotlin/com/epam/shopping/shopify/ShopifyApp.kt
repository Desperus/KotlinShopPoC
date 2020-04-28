package com.epam.shopping.shopify

import com.epam.shopping.util.loggerFor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@Component
class ShopifyApp(@Qualifier("shopifyWebClient") private val webClient: WebClient) {

    private val log = loggerFor(ShopifyApp::class.java)

    fun fetchProducts(): List<Product> {
        log.info("Shopify product request start")

        val response = webClient.get()
                .uri(GET_PRODUCTS_URL)
                .retrieve()
                .bodyToMono(GenericShopifyResponse::class.java)
                .block(Duration.ofSeconds(10))

        log.info("Shopify product request end")
        return response?.products!!
    }

    companion object {
        const val GET_PRODUCTS_URL = "/products.json"
    }

}