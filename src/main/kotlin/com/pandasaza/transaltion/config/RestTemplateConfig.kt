package com.pandasaza.transaltion.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.apache.http.impl.client.HttpClientBuilder
import org.omg.CORBA.portable.ResponseHandler
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpRequest
import org.springframework.http.client.*
import org.springframework.web.client.RestTemplate
import java.util.concurrent.TimeUnit

@Configuration
class RestTemplateConfig(private val restTemplateBuilder: RestTemplateBuilder) {

    @Value("\${papago.api-key}")
    lateinit var apiKey : String

    @Value("\${papago.api-secret}")
    lateinit var apiSecret : String

    private fun createFactory() : ClientHttpRequestFactory{
        var factory = HttpComponentsClientHttpRequestFactory()
        factory.setConnectTimeout(20000)
        factory.setReadTimeout(20000)    // read timeout

        var httpClient = HttpClientBuilder.create()
            .setMaxConnPerRoute(50) // Connection pool on Route
            .setMaxConnTotal(300)   // Connection pool : Max Connection Pool
            // Idle Connection Shutdown on periodically, Killed unused connection on keep-alive time.
            .evictIdleConnections(20000L, TimeUnit.MILLISECONDS)
            .build()
        factory.httpClient = httpClient
        return factory
    }

    @Bean
    fun restTemplate():RestTemplate{
        val restTemplate = restTemplateBuilder
            .defaultHeader("Content-type", "application/json")
            .defaultHeader("X-Naver-Client-Id","eejjkq0Z4PYxSHsyFX1U")
            .defaultHeader("X-Naver-Client-Secret","gbDSE4CinP")
            .build()
            restTemplate.requestFactory = createFactory()
        restTemplate.interceptors = listOf(object : RequestResponseLoggingInterceptor(){})
        return restTemplate
    }
    @Bean
    fun gson(): Gson = GsonBuilder()
        .setPrettyPrinting()
        .disableHtmlEscaping()
        .create()

    open class RequestResponseLoggingInterceptor : ClientHttpRequestInterceptor {
        private val logger = LoggerFactory.getLogger(RequestResponseLoggingInterceptor::class.java)
        override fun intercept(
            request: HttpRequest,
            body: ByteArray,
            execution: ClientHttpRequestExecution
        ): ClientHttpResponse {

            val response = execution.execute(request,body)
            logger.info("uri : ${request.uri} -- statusCode : ${response.statusCode}")
            return response
        }
    }

}