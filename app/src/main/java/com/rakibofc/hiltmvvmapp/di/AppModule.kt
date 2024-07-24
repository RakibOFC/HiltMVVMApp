package com.rakibofc.hiltmvvmapp.di

import android.content.Context
import androidx.room.Room
import com.rakibofc.hiltmvvmapp.data.contact.ContactDao
import com.rakibofc.hiltmvvmapp.data.contact.ContactDatabase
import com.rakibofc.hiltmvvmapp.data.contact.ContactRepository
import com.rakibofc.hiltmvvmapp.data.note.NoteDao
import com.rakibofc.hiltmvvmapp.data.note.NoteDatabase
import com.rakibofc.hiltmvvmapp.data.note.NoteRepository
import com.rakibofc.hiltmvvmapp.domain.usecase.ContactUseCase
import com.rakibofc.hiltmvvmapp.domain.usecase.NoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /**
     * @since
     * 1. What is the difference between sealed class and normal class?
     * 2. What is the difference between sealed class and interface?
     * 3. I have multiple repository which is the best practice for AppModule.
     * Is it best practice to place all repository in same AppModule?
    ```
    sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
    ) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T> : Resource<T>()
    }

    class Resources(
    val data: String? = null,
    val message: String? = null
    ) {
    class   Success
    class Error
    }
    ```
     */

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext mContext: Context): ContactDatabase {
        return Room.databaseBuilder(
            mContext,
            ContactDatabase::class.java,
            "contact_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideNoteDatabase(@ApplicationContext mContext: Context): NoteDatabase {
        return Room.databaseBuilder(
            mContext,
            NoteDatabase::class.java,
            "note_db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideContactDao(mDatabase: ContactDatabase): ContactDao {
        return mDatabase.contactDao()
    }

    @Provides
    @Singleton
    fun provideNoteDao(mDatabase: NoteDatabase): NoteDao {
        return mDatabase.noteDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactDao: ContactDao): ContactUseCase {
        return ContactRepository(contactDao)
    }

    @Provides
    @Singleton
    fun provideNoteRepository(noteDao: NoteDao): NoteUseCase {
        return NoteRepository(noteDao)
    }
}