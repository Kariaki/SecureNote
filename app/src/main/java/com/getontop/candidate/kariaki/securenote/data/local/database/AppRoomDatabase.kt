package com.getontop.candidate.kariaki.securenote.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.getontop.candidate.kariaki.securenote.data.local.database.dao.NoteDao
import com.getontop.candidate.kariaki.securenote.data.local.model.NoteModel


@Database(
    entities = [NoteModel::class],
    exportSchema = false,
    version = 1
)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}