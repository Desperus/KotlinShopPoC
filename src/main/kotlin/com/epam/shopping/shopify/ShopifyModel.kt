package com.epam.shopping.shopify

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.ZonedDateTime

data class GenericShopifyResponse(
        val products: List<Product>?,
        val customers: List<Customer>?
)

data class Product(
        val id: Long,
        val title: String,
        @JsonProperty("created_at") val createdAt: ZonedDateTime
)

data class Customer(
        val id: Long,
        val email: String?,
        @JsonProperty("first_name") val firstName: String,
        @JsonProperty("last_name") val lastName: String
)
