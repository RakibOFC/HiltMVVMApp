package com.rakibofc.hiltmvvmapp.data.contact

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contacts")
data class ContactEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "contact_no") val contactNo: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
) {
    constructor(name: String, contactNo: String) : this(
        id = 0,
        name = name,
        contactNo = contactNo,
        createdAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )

    constructor(name: String, contactNo: String, updatedAt: Long) : this(
        id = 0,
        name = name,
        contactNo = contactNo,
        updatedAt = updatedAt,
        createdAt = System.currentTimeMillis()
    )
}