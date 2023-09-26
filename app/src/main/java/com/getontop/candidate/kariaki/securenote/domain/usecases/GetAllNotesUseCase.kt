package com.getontop.candidate.kariaki.securenote.domain.usecases
import com.getontop.candidate.kariaki.securenote.core.DataState
import com.getontop.candidate.kariaki.securenote.core.toNoteDto
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetAllNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke() = repository.getAllNotes()
        .map { it.map { it1->it1.toNoteDto() } }
}