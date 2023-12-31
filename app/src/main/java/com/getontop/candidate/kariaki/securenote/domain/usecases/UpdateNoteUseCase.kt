package com.getontop.candidate.kariaki.securenote.domain.usecases

import com.getontop.candidate.kariaki.securenote.core.DataState
import com.getontop.candidate.kariaki.securenote.domain.dto.InsertNoteDto
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class UpdateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(param: InsertNoteDto) = flow {
        emit(DataState.Loading())
        try {
            val findNote = (
                    repository.getNoteById(param.id!!) ?: kotlin.run {
                        emit(DataState.Error("Not not found"))
                        return@flow
                    }).copy(
                timestamp = System.currentTimeMillis(),
                description = param.description,
                tittle = param.title
            )
            val result = repository.updateNote(findNote)
            emit(DataState.Success(result))
        } catch (e: Exception) {
            emit(DataState.Error(e.localizedMessage))
        }
    }
}