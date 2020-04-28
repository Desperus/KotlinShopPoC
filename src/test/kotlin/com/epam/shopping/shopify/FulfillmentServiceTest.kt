package com.epam.shopping.shopify

import com.epam.shopping.BaseIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

class FulfillmentServiceTest : BaseIntegrationTest() {

    @LocalServerPort
    private lateinit var localPort: String

    @Test
    fun shouldFetchTrackingNumbers() {
        // given

        // when
        val response = WebClient.create("http://localhost:$localPort/fulfillment")
                .get()
                .uri("/fetch_tracking_numbers.json?shop=testshop.myshopify.com&order_names[]=#1001.1&order_names[]=#1002.1&order_names[]=#1003.2")
                .retrieve()
                .bodyToMono(TrackingResponse::class.java)
                .block(Duration.ofSeconds(10))

        // then
        checkNotNull(response)
        assert(response.success)
    }

    @Test
    fun shouldFetchWholeStock() {
        // given

        // when
        val response = WebClient.create("http://localhost:$localPort/fulfillment")
                .get()
                .uri("/fetch_stock.json?shop=testshop.myshopify.com")
                .retrieve()
                .bodyToMono(fetchMapType)
                .block(Duration.ofSeconds(10))

        // then
        checkNotNull(response)
        assert(response.containsKey("123"))
        assert(response.containsKey("111"))
    }

    @Test
    fun shouldFetchExistingStock() {
        // given

        // when
        val response = WebClient.create("http://localhost:$localPort/fulfillment")
                .get()
                .uri("/fetch_stock.json?sku=123&shop=testshop.myshopify.com&max_retries=3&timestamp=1532548742")
                .retrieve()
                .bodyToMono(fetchMapType)
                .block(Duration.ofSeconds(10))

        // then
        checkNotNull(response)
        assert(response.containsKey("123"))
        assert(!response.containsKey("111"))
    }

    companion object {
        val fetchMapType = object: ParameterizedTypeReference<Map<String, String>>() {}
    }

}