package com.epam.shopping.shopify

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

data class GenericShopifyResponse(
        val products: List<Product>?
)

data class Product(
        val id: Long,
        val title: String,
        @JsonProperty("created_at") val createdAt: ZonedDateTime
)
