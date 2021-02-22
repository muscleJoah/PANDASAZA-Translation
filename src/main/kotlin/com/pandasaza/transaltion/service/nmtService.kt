package com.pandasaza.transaltion.service

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.pandasaza.transaltion.dto.nmtDTO
import com.pandasaza.transaltion.dto.requestDTO
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.postForEntity


@Service
class nmtService(private val restTemplate: RestTemplate) {

    companion object{
        const val apiURL :String = "https://openapi.naver.com/v1/papago/n2mt"
    }
    fun translate( source : String, target : String , text : String ): ResponseEntity<String> {
        var gson = GsonBuilder().create()
        val request = requestDTO(source,target,text)
        var json = gson.toJson(request)
        return restTemplate.postForEntity("$apiURL" ,json , String::class.java)
    }
        }

// ParameterizedTypeReference :: Array 형태의 JSON Object 매핑
inline fun <reified T> typeReference() = object : ParameterizedTypeReference<T>() {}

