package com.rakibofc.hiltmvvmapp.data.note

import com.rakibofc.hiltmvvmapp.domain.model.Note
import com.rakibofc.hiltmvvmapp.domain.usecase.NoteUseCase
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val noteDao: NoteDao
) : NoteUseCase {

    override suspend fun upsertNote(note: NoteEntity) {
        noteDao.upsertNote(note)
    }

    override suspend fun getNoteList(): Resource<List<Note>> {
        return try {
            Resource.Success(noteDao.getNoteList().map { it.toNote() })
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }
}