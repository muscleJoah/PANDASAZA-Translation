package com.pandasaza.transaltion.config

import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.ExchangeFilterFunction
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono


@Configuration
class WebClientConfig {

    @Value("\${papago.api-key}")
    lateinit var apiKey : String

    @Value("\${papago.api-secret}")
    lateinit var apiSecret : String

    private val logger = LoggerFactory.getLogger(WebClientConfig::class.java)

    @Bean
    fun webClient(builder : WebClient.Builder) : WebClient =
        builder
            .filter(requestLoggerFilter())
            .filter(responseLoggerFilter())
            .defaultHeader("Content-type", "application/json")
            .defaultHeader("X-Naver-Client-Id","eejjkq0Z4PYxSHsyFX1U")
            .defaultHeader("X-Naver-Client-Secret","gbDSE4CinP")
            .build()

    fun requestLoggerFilter() = ExchangeFilterFunction.ofRequestProcessor {
        logger.info("Logging request: ${it.method()} ${it.url()}")

        Mono.just(it)
    }

    fun responseLoggerFilter() = ExchangeFilterFunction.ofResponseProcessor {
        logger.info("Response status code: ${it.statusCode()}")

        Mono.just(it)
    }

}