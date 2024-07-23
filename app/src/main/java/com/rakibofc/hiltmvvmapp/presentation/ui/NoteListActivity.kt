package com.rakibofc.hiltmvvmapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.databinding.ActivityNoteListBinding
import com.rakibofc.hiltmvvmapp.domain.model.Note
import com.rakibofc.hiltmvvmapp.presentation.adapter.NotesAdapter
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import com.rakibofc.hiltmvvmapp.presentation.viewmodel.NoteListViewModel

class NoteListActivity : AppCompatActivity(), NotesAdapter.OnItemClickListener {

    private val viewModel: NoteListViewModel by viewModels()
    private lateinit var binding: ActivityNoteListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityNoteListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.notes.observe(this) {
            handleNoteData(it)
        }
    }

    private fun handleNoteData(resource: Resource<List<Note>>?) {

        with(binding) {

            when (resource) {

                is Resource.Loading -> {
                    rvNotes.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {

                    val noteList = resource.data
                    noteList?.let {

                        if (it.isNotEmpty()) {

                            val notesAdapter = NotesAdapter(applicationContext, it)
                            notesAdapter.setOnItemClickListener(this@NoteListActivity)
                            rvNotes.adapter = notesAdapter

                            llcPbStatus.visibility = View.GONE
                            rvNotes.visibility = View.VISIBLE
                        } else {
                            rvNotes.visibility = View.GONE
                            progressBar.visibility = View.GONE
                            tvStatusMsg.text = getString(R.string.no_contact_msg)
                        }
                    }
                }

                is Resource.Error -> {
                    rvNotes.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    tvStatusMsg.text = resource.message
                }

                null -> {
                    rvNotes.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    tvStatusMsg.text = getString(R.string.no_data_msg)
                }
            }
        }
    }

    override fun onItemClick(position: Int, note: Note) {


    }
}