package com.epam.shopping.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebConfig(
        @Value("\${pocapp.output.url}") private val outputBaseUrl: String
) {

    @Bean
    fun webClient() = WebClient
            .builder()
            .baseUrl(outputBaseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON.type)
            .build()

}