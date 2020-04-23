package com.epam.shopping

import com.epam.shopping.config.TestKafkaConfig
import com.epam.shopping.model.ProductChangeEvent
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.TimeUnit

@SpringBootTest(classes = [TestKafkaConfig::class])
@ActiveProfiles("test")
class ShoppingApplicationTests {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, ProductChangeEvent>

    @Value("\${pocapp.topic.product}")
    private lateinit var productTopic: String

    @Autowired
    private lateinit var kafkaBroker: EmbeddedKafkaBroker

    @Autowired
    private lateinit var kafkaAdmin: KafkaAdmin

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
