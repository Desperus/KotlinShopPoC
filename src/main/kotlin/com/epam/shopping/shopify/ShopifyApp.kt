package com.epam.shopping.shopify

import com.epam.shopping.util.loggerFor
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody
import org.springframework.web.reactive.function.client.awaitExchange

@Component
class ShopifyApp(@Qualifier("shopifyWebClient") private val webClient: WebClient) {

    private val log = loggerFor(ShopifyApp::class.java)

    suspend fun fetchProducts(): List<Product> {
        log.info("Shopify product request start")

        return webClient.get()
                .uri(GET_PRODUCTS_URL)
                .awaitExchange()
                .awaitBody<GenericShopifyResponse>()
                .products!!
    }

    suspend fun fetchCustomers(): List<Customer> {
        log.info("Shopify customer request start")

        return webClient.get()
                .uri(GET_CUSTOMERS_URL)
                .awaitExchange()
                .awaitBody<GenericShopifyResponse>()
                .customers!!
    }

    companion object {
        const val GET_PRODUCTS_URL = "/products.json"
        const val GET_CUSTOMERS_URL = "/customers.json"
    }

}