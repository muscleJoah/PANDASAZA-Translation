package com.pandasaza.transaltion.controller

import com.pandasaza.transaltion.dto.messageDTO
import com.pandasaza.transaltion.dto.translateDTO
import com.pandasaza.transaltion.service.nmtService
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping


@Controller
@RequestMapping("/api")
class translationController(private val nmtService: nmtService) {

    @PostMapping("/translate/{source}/{target}/{text}")
    fun postTranslate(@PathVariable("source") source : String,
                      @PathVariable("target") target : String,
                      @PathVariable("text") text : String): ResponseEntity<translateDTO> {
        val result = nmtService.translate(source, target, text)
        return result
    }
}