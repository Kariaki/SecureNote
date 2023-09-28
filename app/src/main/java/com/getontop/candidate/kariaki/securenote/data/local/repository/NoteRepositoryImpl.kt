package com.getontop.candidate.kariaki.securenote.data.local.repository
import com.getontop.candidate.kariaki.securenote.data.local.database.dao.NoteDao
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(private val noteDao: NoteDao):NoteRepository {
    override suspend fun insertNote(note: NoteModel) = noteDao.insertNote(note)
    override suspend fun deleteNoteById(id: Int)  = noteDao.deleteNoteById(id)
    override suspend fun updateNote(note: NoteModel) = noteDao.updateNote(note)
    override fun getAllNotes(): Flow<List<NoteModel>>  = noteDao.getAllNotes()
    override suspend fun getNoteById(id: Int): NoteModel? = noteDao.getNoteById(id)
}
