package com.getontop.candidate.kariaki.securenote.domain.dto

import androidx.room.PrimaryKey

data class NoteDto(
    val tittle: String,
    val description: String,
    val id: Int,
    val timestamp:Long,
)
