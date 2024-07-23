package com.rakibofc.hiltmvvmapp.data.note

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: NoteEntity)

    @Query("SELECT * FROM notes")
    suspend fun getNoteList(): List<NoteEntity>
}