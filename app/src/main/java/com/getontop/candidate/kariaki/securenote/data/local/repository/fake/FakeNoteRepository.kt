package com.getontop.candidate.kariaki.securenote.data.local.repository.fake
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
class FakeNoteRepository:NoteRepository {
    private val notes:MutableList<NoteModel> = mutableListOf(
        NoteModel("Spotify password","the password for spotify is askyourdaddy12345",0,0,),
        NoteModel("Shopping list","he said I should bring everything from the store and get the job done",1,0,),
        NoteModel("Todo for digicore task","the back-office-app is not even useful",2,0),
        NoteModel("","I hope everything is great though, just trying to check on you",3,0)
    )
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

    override suspend fun getNoteById(id: Int): NoteModel? {
        return notes.firstOrNull { it.id==id }
    }
}