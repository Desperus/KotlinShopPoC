package com.epam.shopping.shopify

import com.epam.shopping.config.TestKafkaConfig
import com.epam.shopping.config.TestWebConfig
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.web.reactive.function.client.WebClient
import java.time.Duration

@SpringBootTest(classes = [TestKafkaConfig::class, TestWebConfig::class], webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
class FulfillmentServiceTest {

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
        val response: Map<String, String> = WebClient.create("http://localhost:$localPort/fulfillment")
                .get()
                .uri("/fetch_stock.json?shop=testshop.myshopify.com")
                .retrieve()
                .bodyToMono(Map::class.java) // TODO provide proper generics
                .block(Duration.ofSeconds(10)) as Map<String, String>

        // then
        assert(response.containsKey("123"))
        assert(response.containsKey("111"))
    }

    @Test
    fun shouldFetchExistingStock() {
        // given

        // when
        val response: Map<String, String> = WebClient.create("http://localhost:$localPort/fulfillment")
                .get()
                .uri("/fetch_stock.json?sku=123&shop=testshop.myshopify.com&max_retries=3&timestamp=1532548742")
                .retrieve()
                .bodyToMono(Map::class.java) // TODO provide proper generics
                .block(Duration.ofSeconds(10)) as Map<String, String>

        // then
        assert(response.containsKey("123"))
        assert(!response.containsKey("111"))
    }

}