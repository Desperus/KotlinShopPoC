package com.epam.shopping.shopify

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/fulfillment")
class FulfillmentService {

    @GetMapping("/fetch_tracking_numbers.json")
    @ResponseBody
    fun fetchTrackingNumbers(@RequestParam shop: String,
                             @RequestParam("order_names[]") orderNames: List<String>): TrackingResponse {
        return TrackingResponse(emptyMap(), "Retrieved", true)
    }

    @GetMapping("/fetch_stock.json")
    @ResponseBody
    fun fetchStock(@RequestParam shop: String,
                   @RequestParam("sku", required = false) skus: List<String>?,
                   @RequestParam("max_retries", required = false) maxRetries: Int?,
                   @RequestParam(required = false) timestamp: Long?): Map<String, String> {
        return if (skus.isNullOrEmpty()) fetchAll() else fetchBySkus(skus)
    }

    private fun fetchAll(): Map<String, String> = stock

    private fun fetchBySkus(skus: List<String>) = stock.filter { (k, _) -> skus.contains(k) }

    companion object StockDb {
        val stock = mapOf("123" to "5", "111" to "0")
    }

}

data class TrackingResponse(
        val trackingNumbers: Map<String, String>,
        val message: String,
        val success: Boolean
)