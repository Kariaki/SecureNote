package com.getontop.candidate.kariaki.securenote.domain.dto

data class InsertNoteDto(
    val title:String="",
    val description:String,
    val id:Int? = null
)
