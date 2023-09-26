package com.getontop.candidate.kariaki.securenote.data.local.repository.fake

import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import java.time.Instant
class FakeNoteRepositoryTest {
  private  lateinit var repository: NoteRepository
  @Before
    fun setUp() {
        repository = FakeNoteRepository()
  }
    @Test
    fun insertNote() = runBlocking{
      val now = Instant.now()
        val newNote = NoteModel("Hello how are you","I am coming back from market in an hour",null,now.toEpochMilli(),now.toString())
      repository.insertNote(newNote)
      val notes = repository.getAllNotes()
      val first = notes.first()
      assert(first.isNotEmpty())
    }
    @Test
    fun deleteNoteById() {
    }
    @Test
    fun updateNote() {
    }
    @Test
    fun getAllNotes() {
    }
}