package com.getontop.candidate.kariaki.securenote.data.local.repository.fake
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class FakeNoteRepository():NoteRepository {
    private val notes:MutableList<NoteModel> = mutableListOf()
    override suspend fun insertNote(note: NoteModel) {
        val currentId = notes.size+1
        val newNote = note.copy(id = currentId)
       notes.add(newNote)
    }
    override suspend fun deleteNoteById(id: Int) {
       notes.removeIf { it.id==id }
    }
    override suspend fun updateNote(note: NoteModel) {
        val index = notes.indexOfFirst { it.id==note.id }
        if(index==-1)return
        notes[index] = note
    }
    override fun getAllNotes(): Flow<List<NoteModel>>  = flow {
        emit(notes)
    }
}