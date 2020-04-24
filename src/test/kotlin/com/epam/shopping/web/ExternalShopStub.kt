package com.epam.shopping.web

import com.epam.shopping.model.ProductChangeEvent
import com.epam.shopping.util.loggerFor
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicInteger

@RestController("/shop")
class ExternalShopStub {

    private val log = loggerFor(ExternalShopStub::class.java)
    private val updateCount = AtomicInteger(0)

    @PostMapping("/product/{id}")
    fun updateProduct(@PathVariable id: Long, productChangeEvent: ProductChangeEvent) {
        updateCount.addAndGet(1)
        log.info("Updated good in shop: id=$id, product=$productChangeEvent")
    }

    fun currentUpdates() = updateCount.get()


}