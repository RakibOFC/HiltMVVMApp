package com.rakibofc.hiltmvvmapp.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.databinding.ActivityContactListBinding
import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.presentation.adapter.ContactsAdapter
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import com.rakibofc.hiltmvvmapp.presentation.viewmodel.ContactViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactListActivity : AppCompatActivity(), ContactsAdapter.OnItemClickListener {

    private val viewModel: ContactViewModel by viewModels()
    private lateinit var binding: ActivityContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.contacts.observe(this) {
            handleContactData(it)
        }

        binding.fabAdd.setOnClickListener {
            startActivity(Intent(applicationContext, UpsertContactActivity::class.java))
        }
    }

    private fun handleContactData(resource: Resource<List<Contact>>?) {

        with(binding) {

            when (resource) {

                is Resource.Loading -> {
                    rvContacts.visibility = View.GONE
                    progressBar.visibility = View.VISIBLE
                }

                is Resource.Success -> {

                    val contactList = resource.data
                    contactList?.let {

                        if (it.isNotEmpty()) {

                            val contactsAdapter = ContactsAdapter(applicationContext, it)
                            contactsAdapter.setOnItemClickListener(this@ContactListActivity)
                            rvContacts.adapter = contactsAdapter

                            llcPbStatus.visibility = View.GONE
                            rvContacts.visibility = View.VISIBLE
                        } else {
                            rvContacts.visibility = View.GONE
                            progressBar.visibility = View.GONE
                            tvStatusMsg.text = getString(R.string.no_contact_msg)
                        }
                    }
                }

                is Resource.Error -> {
                    rvContacts.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    tvStatusMsg.text = resource.message
                }

                null -> {
                    rvContacts.visibility = View.GONE
                    progressBar.visibility = View.GONE
                    tvStatusMsg.text = getString(R.string.no_data_msg)
                }
            }
        }
    }

    override fun onItemClick(position: Int, contact: Contact) {

        val intent = Intent(applicationContext, UpsertContactActivity::class.java)
            .apply {
                putExtra(Contact.CONTACT_ID_KEY, contact.id)
                putExtra(Contact.NAME_KEY, contact.name)
                putExtra(Contact.PHONE_KEY, contact.contactNo)
                putExtra(Contact.CREATED_AT_KEY, contact.createdAt)
            }
        startActivity(intent)
    }
}