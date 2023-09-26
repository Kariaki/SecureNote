package com.getontop.candidate.kariaki.securenote.domain.usecases
import com.getontop.candidate.kariaki.securenote.core.DataState
import com.getontop.candidate.kariaki.securenote.core.toNoteModel
import com.getontop.candidate.kariaki.securenote.domain.dto.InsertNoteDto
import com.getontop.candidate.kariaki.securenote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
class InsertNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(param:InsertNoteDto)= flow {
        emit(DataState.Loading())
        try {
            val result =  repository.insertNote(param.toNoteModel())
            emit(DataState.Success(result))
        }catch (e:Exception){
            emit(DataState.Error(e.localizedMessage))
        }
    }
}