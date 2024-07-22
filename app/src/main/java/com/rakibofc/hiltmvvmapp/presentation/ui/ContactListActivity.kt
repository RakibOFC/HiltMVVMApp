package com.rakibofc.hiltmvvmapp.presentation.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.rakibofc.hiltmvvmapp.R
import com.rakibofc.hiltmvvmapp.data.contact.ContactEntity
import com.rakibofc.hiltmvvmapp.databinding.ActivityContactListBinding
import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.presentation.adapter.ContactsAdapter
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import com.rakibofc.hiltmvvmapp.presentation.viewmodel.ContactListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ContactListActivity : AppCompatActivity() {

    private val userViewModel: ContactListViewModel by viewModels()
    private lateinit var binding: ActivityContactListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityContactListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        userViewModel.contacts.observe(this) {
            handleContactData(it)
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
                            rvContacts.adapter = ContactsAdapter(applicationContext, it)
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
}