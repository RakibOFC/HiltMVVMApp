package com.rakibofc.hiltmvvmapp.data.contact

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface ContactDao {
    @Upsert
    suspend fun upsertContact(contact: ContactEntity)

    @Query("SELECT * FROM contacts")
    suspend fun getContactList(): List<ContactEntity>
}