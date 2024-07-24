package com.rakibofc.hiltmvvmapp.domain.model

data class Contact(
    val id: Long,
    val name: String,
    val contactNo: String,
    val createdAt: Long
) {
    companion object {
        const val CONTACT_ID_KEY = "id"
        const val NAME_KEY = "name"
        const val PHONE_KEY = "phone"
        const val CREATED_AT_KEY = "createdAt"
    }
}

data class Note(
    val id: Long,
    val noteTitle: String,
    val noteText: String,
    val createdAt: Long
) {
    companion object {
        const val NOTE_ID_KEY = "noteId"
        const val NOTE_TITLE_KEY = "noteTitle"
        const val NOTE_TEXT_KEY = "noteText"
        const val CREATED_AT_KEY = "createdAt"
    }
}