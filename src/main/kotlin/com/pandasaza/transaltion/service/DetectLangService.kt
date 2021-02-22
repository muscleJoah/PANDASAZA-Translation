package com.pandasaza.transaltion.service

import com.google.gson.GsonBuilder
import com.pandasaza.transaltion.dto.detectLangDTO.langcodeDTO
import com.pandasaza.transaltion.dto.detectLangDTO.parameterDTO
import com.pandasaza.transaltion.dto.nmtDTO.requestDTO
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class DetectLangService(private val restTemplate: RestTemplate) {

    companion object {
        const val apiURL  = "https://openapi.naver.com/v1/papago/detectLangs"
    }

    fun detectLang(text  : String): ResponseEntity<langcodeDTO> {
        var gson = GsonBuilder().create()
        val request = parameterDTO(text)
        var json = gson.toJson(request)
    return restTemplate.postForEntity("$apiURL", json , langcodeDTO::class.java )
    }

}