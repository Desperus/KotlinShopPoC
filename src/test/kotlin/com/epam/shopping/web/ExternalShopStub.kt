package com.epam.shopping.web

import com.epam.shopping.model.ProductChangeEvent
import com.epam.shopping.util.loggerFor
import org.springframework.web.bind.annotation.*
import java.util.concurrent.atomic.AtomicInteger

@RestController
@RequestMapping("/shop")
class ExternalShopStub {

    private val log = loggerFor(ExternalShopStub::class.java)
    private val updateCount = AtomicInteger(0)

    @PostMapping("/product/{id}")
    fun updateProduct(@PathVariable("id") id: Long, @RequestBody productChangeEvent: ProductChangeEvent) {
        updateCount.addAndGet(1)
        log.info("Updated good in shop: id=$id, product=$productChangeEvent")
    }

    fun currentUpdates() = updateCount.get()

}