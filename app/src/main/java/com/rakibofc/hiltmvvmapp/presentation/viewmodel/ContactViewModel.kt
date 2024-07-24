package com.rakibofc.hiltmvvmapp.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakibofc.hiltmvvmapp.data.contact.ContactEntity
import com.rakibofc.hiltmvvmapp.data.contact.ContactRepository
import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val contactRepository: ContactRepository
) : ViewModel() {

    private val _contacts = MutableLiveData<Resource<List<Contact>>>()
    val contacts: LiveData<Resource<List<Contact>>> get() = _contacts

    init {
        fetchContacts()
    }

    suspend fun upsertContact(contact: ContactEntity) {
        viewModelScope.launch {
            contactRepository.upsertContact(contact)
        }
    }

    private fun fetchContacts() {
        viewModelScope.launch {
            _contacts.postValue(contactRepository.getContactList())
        }
    }
}