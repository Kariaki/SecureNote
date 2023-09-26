package com.getontop.candidate.kariaki.securenote.data.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface NoteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNote(note: NoteModel)
    @Query("delete  from notes where id like:noteId")
    suspend fun deleteNoteById(noteId:Int)
    @Update
    suspend fun updateNote(node: NoteModel)
    @Query("select * from notes order by timestamp desc ")
    fun getAllNotes(): Flow<List<NoteModel>>

    @Query("select * from notes where id like:noteId")
    fun getNoteById(noteId:Int):NoteModel?

}