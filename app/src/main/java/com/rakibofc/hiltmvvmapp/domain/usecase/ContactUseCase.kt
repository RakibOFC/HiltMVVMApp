package com.rakibofc.hiltmvvmapp.domain.usecase

import com.rakibofc.hiltmvvmapp.data.contact.ContactEntity
import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.presentation.util.Resource

interface ContactUseCase {
    suspend fun upsertContact(contact: ContactEntity)
    suspend fun getContactList(): Resource<List<Contact>>
}