package com.pandasaza.transaltion.controller

import com.pandasaza.transaltion.dto.detectLangDTO.langcodeDTO
import com.pandasaza.transaltion.dto.nmtDTO.translateDTO
import com.pandasaza.transaltion.service.DetectLangService
import com.pandasaza.transaltion.service.NmtService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/api")
class translationController(private val nmtService: NmtService , private val detectLangService: DetectLangService) {

    @PostMapping("/translate/{source}/{target}/{text}")
    fun postTranslate(@PathVariable("source") source : String,
                      @PathVariable("target") target : String,
                      @PathVariable("text") text : String): ResponseEntity<translateDTO> {
        val result = nmtService.translate(source, target, text)
        return result
    }

    @PostMapping("/detectlang/{lang}")
    fun postdetect(@PathVariable("lang")text : String): ResponseEntity<langcodeDTO> {
        val result = detectLangService.detectLang(text)
        return result
    }
}