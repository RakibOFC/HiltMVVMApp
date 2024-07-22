package com.rakibofc.hiltmvvmapp.di

import android.content.Context
import androidx.room.Room
import com.rakibofc.hiltmvvmapp.data.contact.ContactDao
import com.rakibofc.hiltmvvmapp.data.contact.ContactDatabase
import com.rakibofc.hiltmvvmapp.data.contact.ContactRepository
import com.rakibofc.hiltmvvmapp.domain.usecase.ContactUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext mContext: Context): ContactDatabase {
        return Room.databaseBuilder(mContext, ContactDatabase::class.java, "contact_db").build()
    }

    @Provides
    @Singleton
    fun provideContactDao(mDatabase: ContactDatabase): ContactDao {
        return mDatabase.contactDao()
    }

    @Provides
    @Singleton
    fun provideContactRepository(contactDao: ContactDao): ContactUseCase {
        return ContactRepository(contactDao)
    }
}