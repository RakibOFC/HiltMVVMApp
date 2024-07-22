package com.rakibofc.hiltmvvmapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.rakibofc.hiltmvvmapp.databinding.ItemContainerContactBinding
import com.rakibofc.hiltmvvmapp.domain.model.Contact

class ContactsAdapter(
    val context: Context,
    val contactList: List<Contact>
) : RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val inflater = LayoutInflater.from(context)
        val binding = ItemContainerContactBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(position)
    }

    inner class ViewHolder(binding: ItemContainerContactBinding, itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(position: Int) {

        }
    }
}