package com.epam.shopping

import com.epam.shopping.model.ProductChangeEvent
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.TimeUnit

@SpringBootTest
@ActiveProfiles("test")
class ShoppingApplicationTests {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, ProductChangeEvent>

    @Value("\${pocapp.topic.product}")
    private lateinit var productTopic: String

    @Test
    fun contextLoads() {
    }

    @Test
    fun `when multiple change events sent they are printed to console`() {
        // given
        val productChangeEvent = ProductChangeEvent(1L, "Coffee", 9.0)

        // when
        val future = kafkaTemplate.send(productTopic, "someKey", productChangeEvent)
        future.get(10, TimeUnit.SECONDS)

        // then
    }

}
