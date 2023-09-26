package com.getontop.candidate.kariaki.securenote.data.local.repository
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.getontop.candidate.kariaki.securenote.data.local.database.AppRoomDatabase
import com.getontop.candidate.kariaki.securenote.data.local.database.dao.NoteDao
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.Instant
@RunWith(AndroidJUnit4::class)
class NoteRepositoryImplTest {
    private lateinit var database: AppRoomDatabase
    private lateinit var noteDao: NoteDao
    private lateinit var repository: NoteRepositoryImpl
    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        noteDao = database.noteDao()
        repository = NoteRepositoryImpl(noteDao)
    }
    @After
    fun tearDown() {
        database.close()
    }
    @Test
    fun insertNote() = runBlocking {
        insertNewNote(0)
        val allNotes = repository.getAllNotes().first()
        assert(allNotes.isNotEmpty())
    }
    private fun getNewNote(index: Int): NoteModel {
        val now = Instant.now()
        return NoteModel(
            "Hello how are you $index",
            "I am coming back from market in an hour $index",
            null,
            now.toEpochMilli(),
            now.toString()
        )
    }
    private suspend fun insertNewNote(index: Int) = runBlocking {
        val newNote = getNewNote(index)
        repository.insertNote(newNote)
    }
    @Test
    fun deleteNoteById() = runBlocking {
        for (i in 0..5) {
            insertNewNote(i)
        }
        val allNotes = repository.getAllNotes().first()
        val firstNoteItem = allNotes.firstOrNull() ?: return@runBlocking
        repository.deleteNoteById(firstNoteItem.id!!)
        val latestNote = repository.getAllNotes().first()
        assert(allNotes.size != latestNote.size)
    }
    @Test
    fun updateNote() = runBlocking {
        insertNewNote(0)
        val allNotes = repository.getAllNotes().first()
        var firstNoteItem = allNotes.firstOrNull() ?: return@runBlocking
        firstNoteItem = firstNoteItem.copy(tittle = "Hope everything is fine")
        repository.updateNote(firstNoteItem) //room operation
        val newAllNotes = repository.getAllNotes().first()
        val newFirstNoteItem = newAllNotes.firstOrNull() ?: return@runBlocking
        assert(firstNoteItem.tittle == newFirstNoteItem.tittle)
    }
    @Test
    fun getAllNotes() {
    }
}
