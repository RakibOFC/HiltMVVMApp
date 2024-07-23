package com.rakibofc.hiltmvvmapp.domain.usecase

import com.rakibofc.hiltmvvmapp.data.note.NoteEntity
import com.rakibofc.hiltmvvmapp.domain.model.Note
import com.rakibofc.hiltmvvmapp.presentation.util.Resource

interface NoteUseCase {
    suspend fun upsertNote(note: NoteEntity)
    suspend fun getNoteList(): Resource<List<Note>>
}