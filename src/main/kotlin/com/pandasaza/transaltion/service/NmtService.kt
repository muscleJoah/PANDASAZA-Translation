package com.pandasaza.transaltion.service

import com.google.gson.GsonBuilder
import com.pandasaza.transaltion.dto.nmtDTO.nmtParameterDTO
import com.pandasaza.transaltion.dto.nmtDTO.papagoResponseDTO
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.stream.Collectors
import java.util.stream.Stream


@Service
class NmtService(private val restTemplate: RestTemplate , private val webClient: WebClient) {

    companion object{
        const val apiURL :String = "https://openapi.naver.com/v1/papago/n2mt"
    }
    /*fun translate( source : String, target : String , text : String ): ResponseEntity<translateDTO> {
        var gson = GsonBuilder().create()
        val request = requestDTO(source,target,text)
        var json = gson.toJson(request)
        return restTemplate.postForEntity("$apiURL" ,json , translateDTO::class.java)
    }*/

    fun translate(source : String, target :String, text: String): papagoResponseDTO? {
        var gson = GsonBuilder().create()
        val request = nmtParameterDTO(source,target,text)
        var json = gson.toJson(request)

        return webClient.post()
            .uri("$apiURL")
            .body(BodyInserters.fromValue(json))
            .retrieve()
            .bodyToMono(papagoResponseDTO::class.java)
            .block()




            //.exchangeToFlux{res -> return@exchangeToFlux res.bodyToFlux (papagoResponseDTO::class.java)}
    }
}

