package com.epam.shopping.shopify

import com.epam.shopping.BaseIntegrationTest
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired

class ShopifyAppTest : BaseIntegrationTest() {

    @Autowired
    private lateinit var shopifyApp: ShopifyApp

    @Test
    fun shouldFetchProducts() {
        // given
        // when
        runBlocking {
            val products = shopifyApp.fetchProducts()

            // then
            assert(products.size == 1)
            assert(products[0].title == "Example T-Shirt")
        }
    }

    @Test
    fun shouldFetchCustomers() {
        // given
        // when
        runBlocking {
            val customers = shopifyApp.fetchCustomers()

            // then
            assert(customers.size == 1)
            assert(customers[0].firstName == "Some")
            assert(customers[0].lastName == "Guy")
        }
    }

    @Test
    fun concurrentNonBlockingRequestsExample() {
        // given
        // when
        runBlocking {
            val products = async { shopifyApp.fetchProducts() }
            val customers = async { shopifyApp.fetchCustomers() }

            // then
            print("Obtained products=${products.await()}, customers=${customers.await()}")
        }
    }

}