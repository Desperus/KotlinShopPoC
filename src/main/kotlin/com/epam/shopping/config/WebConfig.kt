package com.epam.shopping.config

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebConfig {

    @Bean("externalWebClient")
    fun webClient(@Value("\${pocapp.output.url}") outputBaseUrl: String) = WebClient
            .builder()
            .baseUrl(outputBaseUrl)
            .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            .build()

    @Bean("shopifyWebClient")
    fun shopifyWebClient(
            @Value("\${pocapp.shopify.shopUrl}") shopUrl: String,
            @Value("\${pocapp.shopify.adminApiUrl}") adminApiUrl: String,
            @Value("\${pocapp.shopify.apikey}") apiKey: String,
            @Value("\${pocapp.shopify.password}") password: String
    ) = WebClient
            .builder()
            .baseUrl("$shopUrl$adminApiUrl")
            .defaultHeaders {
                it.setBasicAuth(apiKey, password, Charsets.UTF_8)
                it.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
            }
            .build()

}