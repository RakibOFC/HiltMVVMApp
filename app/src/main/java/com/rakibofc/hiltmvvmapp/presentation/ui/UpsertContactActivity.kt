package com.rakibofc.hiltmvvmapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.material.textfield.TextInputEditText
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.data.contact.ContactEntity
import com.rakibofc.hiltmvvmapp.databinding.ActivityUpsertContactBinding
import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.presentation.viewmodel.ContactViewModel
import kotlinx.coroutines.launch

class UpsertContactActivity : AppCompatActivity() {

    private val viewModel: ContactViewModel by viewModels()
    private lateinit var binding: ActivityUpsertContactBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpsertContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val name = intent.getStringExtra(Contact.NAME_KEY)
        val phone = intent.getStringExtra(Contact.PHONE_KEY)
        val createdAt = intent.getLongExtra(Contact.CREATED_AT_KEY, 0L)

        with(binding) {
            etName.setText(name)
            etPhone.setText(phone)

            btnSave.setOnClickListener {
                saveContact(createdAt)
            }
        }
    }

    private fun saveContact(createdAt: Long) {

        val name = binding.etName.text.toString().trim()
        val phone = binding.etPhone.text.toString().trim()

        if (name.isEmpty()) {
            showError(binding.etName, R.string.name_text)
            return
        }

        if (phone.isEmpty()) {
            showError(binding.etPhone, R.string.phone_text)
            return
        }

        lifecycleScope.launch {
            if (createdAt == 0L)
                viewModel.upsertContact(ContactEntity(name, phone))
            else
                viewModel.upsertContact(ContactEntity(name, phone, createdAt))
        }
    }

    private fun showError(view: View, @StringRes errorMessageResId: Int) {
        if (view is TextInputEditText) {
            view.error = getString(errorMessageResId)
            view.requestFocus()
        }
    }
}