package com.rakibofc.hiltmvvmapp.domain.model

data class Contact(
    val id: Long,
    val name: String,
    val contactNo: String
)

data class Note(
    val id: Long,
    val noteTitle: String,
    val noteText: String
)