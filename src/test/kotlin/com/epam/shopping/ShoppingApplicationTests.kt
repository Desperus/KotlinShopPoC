package com.epam.shopping

import com.epam.shopping.config.TestKafkaConfig
import com.epam.shopping.config.TestWebConfig
import com.epam.shopping.model.ProductChangeEvent
import com.epam.shopping.web.ExternalShopStub
import org.awaitility.Awaitility
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.kafka.core.KafkaAdmin
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.test.EmbeddedKafkaBroker
import org.springframework.test.context.ActiveProfiles
import java.util.concurrent.TimeUnit

@SpringBootTest(classes = [TestKafkaConfig::class, TestWebConfig::class])
@ActiveProfiles("test")
class ShoppingApplicationTests {

    @Autowired
    private lateinit var kafkaTemplate: KafkaTemplate<String, ProductChangeEvent>

    @Autowired
    private lateinit var shopStub: ExternalShopStub

    @Value("\${pocapp.topic.product}")
    private lateinit var productTopic: String

    @Test
    fun contextLoads() {
    }

    @Test
    fun `when multiple change events sent they are printed to console`() {
        // given
        val productChangeEvent = ProductChangeEvent(1L, "Coffee", 9.0)
        val startUpdates = shopStub.currentUpdates()

        // when
        val future = kafkaTemplate.send(productTopic, "someKey", productChangeEvent)
        future.get(10, TimeUnit.SECONDS)

        // then
        Awaitility.await()
                .atMost(10, TimeUnit.SECONDS)
                .until{ shopStub.currentUpdates() == startUpdates + 1 }
    }

}
