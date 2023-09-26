package com.getontop.candidate.kariaki.securenote.core

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
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

@Composable
fun Int.spaceHeight(){
    Spacer(modifier = Modifier.height(this.dp))
}
@Composable
fun Int.spaceWidth(){
    Spacer(modifier = Modifier.width(this.dp))
}