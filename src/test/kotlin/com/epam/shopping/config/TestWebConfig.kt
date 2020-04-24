package com.epam.shopping.config

import com.epam.shopping.web.ExternalShopStub
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.springframework.context.annotation.Lazy
import org.springframework.context.annotation.Primary
import org.springframework.web.reactive.function.client.WebClient

@TestConfiguration
@Import(ExternalShopStub::class)
class TestWebConfig {
}