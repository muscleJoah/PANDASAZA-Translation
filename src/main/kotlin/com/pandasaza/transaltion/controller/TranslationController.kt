package com.pandasaza.transaltion.controller

import com.pandasaza.transaltion.dto.detectLangDTO.detectResponseDTO
import com.pandasaza.transaltion.dto.nmtDTO.papagoResponseDTO
import com.pandasaza.transaltion.service.DetectLangService
import com.pandasaza.transaltion.service.NmtService
import com.pandasaza.transaltion.service.TranslationService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono


@Controller
@RequestMapping("/api")
class TranslationController(private val nmtService: NmtService, private val detectLangService: DetectLangService, private val translationService: TranslationService) {

    @PostMapping("/translate/{source}/{target}/{text}")
    fun translateTest(
        @PathVariable("source") source: String,
        @PathVariable("target") target: String,
        @PathVariable("text") text: String
    ): Flux<papagoResponseDTO> =
         nmtService.translate(source, target, text)

    @PostMapping("/detectlang/{lang}")
    fun detectTest(@PathVariable("lang") text: String): Flux<detectResponseDTO> =
        detectLangService.detectLang(text)


    @PostMapping("/translateLang/{text}/{target}")
    fun translate(@PathVariable ("text")text : String, @PathVariable("target")target : String){
        translationService.translate(target, text)
    }

}