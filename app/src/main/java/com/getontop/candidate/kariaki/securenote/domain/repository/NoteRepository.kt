package com.getontop.candidate.kariaki.securenote.domain.repository

import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun insertNote(note:NoteModel)
    suspend fun deleteNoteById(id:Int)
    suspend fun updateNote(note: NoteModel)
    fun getAllNotes():Flow<List<NoteModel>>
    suspend fun getNoteById(id: Int):NoteModel?
}