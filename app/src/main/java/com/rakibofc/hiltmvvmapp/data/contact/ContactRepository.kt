package com.rakibofc.hiltmvvmapp.data.contact

import com.rakibofc.hiltmvvmapp.domain.model.Contact
import com.rakibofc.hiltmvvmapp.domain.usecase.ContactUseCase
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import javax.inject.Inject

class ContactRepository @Inject constructor(
    private val contactDao: ContactDao
) : ContactUseCase {

    override suspend fun upsertContact(contact: ContactEntity) {
        contactDao.upsertContact(contact)
    }

    override suspend fun getContactList(): Resource<List<Contact>> {
        return try {
            Resource.Success(contactDao.getContactList().map { it.toContact() })
        } catch (ex: Exception) {
            Resource.Error(ex.message.toString())
        }
    }

    private fun ContactEntity.toContact(): Contact {
        return Contact(id = id, name = name, contactNo = contactNo)
    }
}