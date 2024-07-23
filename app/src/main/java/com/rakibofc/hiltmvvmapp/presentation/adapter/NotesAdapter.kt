package com.rakibofc.hiltmvvmapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rakibofc.hiltmvvmapp.databinding.ItemContainerNoteBinding
import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.domain.model.Note

class NotesAdapter(
    private val context: Context,
    val noteList: List<Note>
) : RecyclerView.Adapter<NotesAdapter.ViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int, note: Note)
    }

    private var onItemClickListener: OnItemClickListener? = null

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val binding = ItemContainerNoteBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(binding: ItemContainerNoteBinding) :
        RecyclerView.ViewHolder(binding.root) {

        private val tvNoteTitle = binding.tvNoteTitle
        private val tvNoteText = binding.tvNoteText

        fun bind(position: Int) {

            val note = noteList[position]

            tvNoteTitle.text = note.noteTitle
            tvNoteText.text = note.noteText

            itemView.setOnClickListener {
                onItemClickListener?.onItemClick(position, note)
            }
        }
    }
}