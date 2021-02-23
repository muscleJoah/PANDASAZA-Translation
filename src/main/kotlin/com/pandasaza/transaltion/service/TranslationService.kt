package com.pandasaza.transaltion.service

import com.pandasaza.transaltion.dto.nmtDTO.papagoResponseDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux


@Service
class TranslationService(private val nmtService: NmtService , private val detectLangService: DetectLangService) {

    fun translate(target : String , text : String): String {

        val source : Flux<String> = detectLangService.detectLang(text).map{it.langCode}
        println("$source")
        return ""
    }
}