package com.epam.shopping.config

import com.epam.shopping.web.ExternalShopStub
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Import

@TestConfiguration
@Import(ExternalShopStub::class)
class TestWebConfig {
}