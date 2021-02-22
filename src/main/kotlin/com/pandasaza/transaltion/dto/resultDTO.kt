package com.pandasaza.transaltion.dto

data class resultDTO (

    val srcLangType : String,
    val tarLangType : String,
    val translatedText : String,
    val engineType : String,
    val pivot : Any?
)