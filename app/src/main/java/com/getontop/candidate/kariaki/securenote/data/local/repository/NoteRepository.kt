package com.getontop.candidate.kariaki.securenote.data.local.repository
import com.getontop.candidate.kariaki.securenote.data.local.database.dao.NoteDao
import javax.inject.Inject

class NoteRepository @Inject constructor(private val noteDao: NoteDao) {
    fun checks(){
    }
}
