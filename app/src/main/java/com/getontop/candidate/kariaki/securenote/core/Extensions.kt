package com.getontop.candidate.kariaki.securenote.core

import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import com.getontop.candidate.kariaki.securenote.domain.dto.InsertNoteDto
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto
import java.time.Instant
fun NoteModel.toNoteDto(): NoteDto =
    NoteDto(this.tittle, this.description, this.id!!, this.timestamp, this.createdAt!!)
fun NoteDto.toNoteModel(): NoteModel =
    NoteModel(this.tittle, this.description, this.id, this.timestamp, this.createdAt)
fun InsertNoteDto.toNoteModel():NoteModel{
    val now = System.currentTimeMillis()
    return NoteModel(this.title, this.description, timestamp =  now, createdAt =  "")
}