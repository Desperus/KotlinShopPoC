package com.epam.shopping.shopify

import com.epam.shopping.BaseIntegrationTest
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ShopifyAppTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var shopifyApp: ShopifyApp

    @Test
    fun shouldPrintProducts() {
        // given
        // when
        val products = shopifyApp.fetchProducts()

        // then
        assert(products.size == 1)
        assert(products[0].title == "Example T-Shirt")
    }

}