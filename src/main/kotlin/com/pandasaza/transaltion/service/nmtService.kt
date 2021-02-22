package com.pandasaza.transaltion.service

import com.google.gson.GsonBuilder
import com.pandasaza.transaltion.dto.messageDTO
import com.pandasaza.transaltion.dto.requestDTO
import com.pandasaza.transaltion.dto.translateDTO
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate


@Service
class nmtService(private val restTemplate: RestTemplate) {

    companion object{
        const val apiURL :String = "https://openapi.naver.com/v1/papago/n2mt"
    }
    fun translate( source : String, target : String , text : String ): ResponseEntity<translateDTO> {
        var gson = GsonBuilder().create()
        val request = requestDTO(source,target,text)
        var json = gson.toJson(request)
        return restTemplate.postForEntity("$apiURL" ,json , translateDTO::class.java)
    }
}

// ParameterizedTypeReference :: Array 형태의 JSON Object 매핑
inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

