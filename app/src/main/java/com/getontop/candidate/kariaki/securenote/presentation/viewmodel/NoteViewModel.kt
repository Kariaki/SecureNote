package com.getontop.candidate.kariaki.securenote.presentation.viewmodel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getontop.candidate.kariaki.securenote.core.DataState
import com.getontop.candidate.kariaki.securenote.core.extractString
import com.getontop.candidate.kariaki.securenote.core.toInsertNoteDto
import com.getontop.candidate.kariaki.securenote.core.toNoteList
import com.getontop.candidate.kariaki.securenote.domain.dto.InsertNoteDto
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto
import com.getontop.candidate.kariaki.securenote.domain.usecases.BaseUseCase
import com.getontop.candidate.kariaki.securenote.exceptions.JsonFileFormatException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel

class NoteViewModel @Inject constructor(
    private val baseUseCase: BaseUseCase
) : ViewModel() {
    private val _notes: Flow<List<NoteDto>> = baseUseCase.getAllNotesUseCase.invoke()
    val notes: Flow<List<NoteDto>> = _notes
    private val _noteActionState: MutableStateFlow<DataState<Unit>> =
        MutableStateFlow(DataState.DataInitial())
    private val _uploadNoteState: MutableStateFlow<DataState<Unit>> =
        MutableStateFlow(DataState.DataInitial())
    val uploadNoteState: StateFlow<DataState<Unit>> = _uploadNoteState
    private val _currentNote: MutableStateFlow<NoteDto?> = MutableStateFlow(null)
    val currentNote: StateFlow<NoteDto?> = _currentNote
    fun createOrUpdateNote(note: NoteDto? = null) {
        _currentNote.value = note
    }
    fun insertNote(note: InsertNoteDto) = viewModelScope.launch {
        baseUseCase.insertNoteUseCase.invoke(note)
    }
    fun updateNote(note: InsertNoteDto) = viewModelScope.launch {
        Log.d("Update note","note updated")
        baseUseCase.updateNoteUseCase.invoke(note).collectLatest {
            _noteActionState.emit(it)
        }
    }
    fun deleteNote(id: Int) = viewModelScope.launch {
        baseUseCase.deleteNoteUseCase.invoke(id)
    }
    fun uploadNotes(uri: Uri,context:Context) = viewModelScope.launch{
        _uploadNoteState.emit(DataState.Loading())
        try {
            val noteList = uri.extractString(context)
                .toNoteList()
            for (i in noteList) {
                delay(200)
                val insertNoteDto = i.toInsertNoteDto()
                baseUseCase.insertNoteUseCase.invoke(insertNoteDto)
            }
            _uploadNoteState.emit(DataState.Success(_unit()))
        }catch (e:JsonFileFormatException){
            _uploadNoteState.emit(DataState.Error(e.localizedMessage!!))
        }catch (e:Exception){
            _uploadNoteState.emit(DataState.Error(e.localizedMessage?:"An error occured"))
        }
    }
    private fun _unit(){}
}
