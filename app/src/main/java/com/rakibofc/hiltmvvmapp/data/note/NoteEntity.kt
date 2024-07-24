package com.rakibofc.hiltmvvmapp.data.note

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rakibofc.hiltmvvmapp.domain.model.Note

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true) val id: Long,
    @ColumnInfo(name = "note_title") val noteTitle: String,
    @ColumnInfo(name = "note_text") val noteText: String,
    @ColumnInfo(name = "created_at") val createdAt: Long,
    @ColumnInfo(name = "updated_at") val updatedAt: Long,
) {
    constructor(noteTitle: String, noteText: String) : this(
        id = 0,
        noteTitle = noteTitle,
        noteText = noteText,
        createdAt = System.currentTimeMillis(),
        updatedAt = System.currentTimeMillis()
    )

    constructor(noteId: Long, noteTitle: String, noteText: String, createdAt: Long) : this(
        id = noteId,
        noteTitle = noteTitle,
        noteText = noteText,
        createdAt = createdAt,
        updatedAt = System.currentTimeMillis()
    )

    fun toNote(): Note {
        return Note(id = id, noteTitle = noteTitle, noteText = noteText, createdAt = createdAt)
    }
}