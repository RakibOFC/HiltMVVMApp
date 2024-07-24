package com.rakibofc.hiltmvvmapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.data.note.NoteEntity
import com.rakibofc.hiltmvvmapp.databinding.ActivityUpsertNoteBinding
import com.rakibofc.hiltmvvmapp.domain.model.Note
import com.rakibofc.hiltmvvmapp.presentation.viewmodel.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UpsertNoteActivity : AppCompatActivity() {

    private val viewModel: NoteViewModel by viewModels()
    private lateinit var binding: ActivityUpsertNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpsertNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val noteId = intent.getLongExtra(Note.NOTE_ID_KEY, 0L)
        val noteTitle = intent.getStringExtra(Note.NOTE_TITLE_KEY)
        val noteText = intent.getStringExtra(Note.NOTE_TEXT_KEY)
        val createdAt = intent.getLongExtra(Note.CREATED_AT_KEY, 0L)

        with(binding) {
            etNoteTitle.setText(noteTitle)
            etNoteText.setText(noteText)

            btnSave.setOnClickListener {
                saveNote(noteId, createdAt)
            }
        }
    }

    private fun saveNote(noteId: Long, createdAt: Long) {

        val noteTitle = binding.etNoteTitle.text.toString().trim()
        val noteText = binding.etNoteText.text.toString().trim()

        if (noteTitle.isEmpty()) {
            showError(binding.etNoteTitle, R.string.title_text)
            return
        }

        if (noteText.isEmpty()) {
            showError(binding.etNoteText, R.string.note_text)
            return
        }

        lifecycleScope.launch {
            if (createdAt == 0L)
                viewModel.upsertNote(NoteEntity(noteTitle, noteText))
            else
                viewModel.upsertNote(NoteEntity(noteId, noteTitle, noteText, createdAt))
        }
    }

    private fun showError(view: View, @StringRes errorMessageResId: Int) {
        if (view is TextInputEditText) {
            view.error = getString(errorMessageResId)
            view.requestFocus()
        }
    }
}