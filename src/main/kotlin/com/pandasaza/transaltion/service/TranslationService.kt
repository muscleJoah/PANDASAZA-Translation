package com.pandasaza.transaltion.service

import com.pandasaza.transaltion.dto.nmtDTO.papagoResponseDTO
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux


@Service
class TranslationService(private val nmtService: NmtService , private val detectLangService: DetectLangService) {

    fun translate(target : String , text : String): String {
        val langCode = detectLangService.detectLang(text)!!.langCode
        val translatedText = nmtService.translate(langCode, target, text)!!.message.result.translatedText
        println("(번역 전 text : $text 언어 : $langCode 타겟 : $target ) 번역 후 text : $translatedText")
        return translatedText
    }
}