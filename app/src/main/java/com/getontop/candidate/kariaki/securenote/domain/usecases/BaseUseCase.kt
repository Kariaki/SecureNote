package com.getontop.candidate.kariaki.securenote.domain.usecases

data class BaseUseCase (
    val deleteNoteUseCase: DeleteNoteUseCase,
    val getAllNotesUseCase: GetAllNotesUseCase,
    val insertNoteUseCase: InsertNoteUseCase,
    val updateNoteUseCase: UpdateNoteUseCase
)