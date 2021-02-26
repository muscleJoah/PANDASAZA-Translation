package com.pandasaza.transaltion.service

import com.google.gson.GsonBuilder
import com.pandasaza.transaltion.dto.detectLangDTO.detectResponseDTO
import com.pandasaza.transaltion.dto.detectLangDTO.detectParameterDTO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Service
class DetectLangService(private val restTemplate: RestTemplate , private val webClient: WebClient) {

    companion object {
        const val apiURL  = "https://openapi.naver.com/v1/papago/detectLangs"
    }


    fun detectLang(text : String): detectResponseDTO? {
        val gson = GsonBuilder().create()
        val request = detectParameterDTO(text)
        val json = gson.toJson(request)
        return webClient.post()
            .uri("$apiURL")
            .body(BodyInserters.fromValue(json))
            .retrieve()
            .bodyToMono(detectResponseDTO::class.java)
            .block()
    }
}