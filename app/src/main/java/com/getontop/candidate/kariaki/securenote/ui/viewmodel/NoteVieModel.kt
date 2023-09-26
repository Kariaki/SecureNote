package com.getontop.candidate.kariaki.securenote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.getontop.candidate.kariaki.securenote.core.DataState
import com.getontop.candidate.kariaki.securenote.core.NonResult
import com.getontop.candidate.kariaki.securenote.domain.dto.InsertNoteDto
import com.getontop.candidate.kariaki.securenote.domain.dto.NoteDto
import com.getontop.candidate.kariaki.securenote.domain.usecases.DeleteNoteUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.GetAllNotesUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.InsertNoteUseCase
import com.getontop.candidate.kariaki.securenote.domain.usecases.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class NoteVieModel @Inject constructor(
    private val insertNoteUseCase: InsertNoteUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getAllNotesUseCase: GetAllNotesUseCase
) : ViewModel() {
    private val _notes: MutableStateFlow<DataState<List<NoteDto>>> = MutableStateFlow(DataState.DataInitial())
    val notes: StateFlow<DataState<List<NoteDto>>> = _notes
    private val _noteActionState:MutableStateFlow<DataState<Unit>> = MutableStateFlow(DataState.DataInitial())
    fun getAllNotes() = viewModelScope.launch {
        _notes.emit(DataState.Loading())
        getAllNotesUseCase.invoke().collectLatest {
            _notes.emit(DataState.Success(it))
        }
    }
    fun insertNote(note:InsertNoteDto)=viewModelScope.launch {
        insertNoteUseCase.invoke(note).collectLatest {
            _noteActionState.emit(it)
        }
    }
    fun updateNote(note:NoteDto)=viewModelScope.launch {
        updateNoteUseCase.invoke(note).collectLatest {
            _noteActionState.emit(it)
        }
    }
    fun deleteNote(id:Int)=viewModelScope.launch {
        deleteNoteUseCase.invoke(id).collectLatest {
            _noteActionState.emit(it)
        }
    }
}