package com.rakibofc.hiltmvvmapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakibofc.hiltmvvmapp.data.note.NoteEntity
import com.rakibofc.hiltmvvmapp.domain.model.Note
import com.rakibofc.hiltmvvmapp.domain.usecase.NoteUseCase
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val noteUseCase: NoteUseCase
) : ViewModel() {

    private val _notes = MutableLiveData<Resource<List<Note>>>()
    val notes: LiveData<Resource<List<Note>>> get() = _notes

    init {
        fetchNotes()
    }

    suspend fun upsertNote(noteEntity: NoteEntity) {
        viewModelScope.launch {
            noteUseCase.upsertNote(noteEntity)
        }
    }

    private fun fetchNotes() {
        viewModelScope.launch {
            _notes.postValue(noteUseCase.getNoteList())
        }
    }
}