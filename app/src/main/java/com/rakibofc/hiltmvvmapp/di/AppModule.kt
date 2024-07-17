package com.rakibofc.hiltmvvmapp.di

import android.content.Context
import androidx.room.Room
import com.rakibofc.hiltmvvmapp.data.local.AppDatabase
import com.rakibofc.hiltmvvmapp.data.local.UserDao
import com.rakibofc.hiltmvvmapp.data.remote.ApiService
import com.rakibofc.hiltmvvmapp.data.remote.UserRemoteDataSource
import com.rakibofc.hiltmvvmapp.data.repository.UserRepository
import com.rakibofc.hiltmvvmapp.domain.repository.IUserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private const val BASE_URL = "https://reqres.in/api/"

    @Provides
    @Singleton
    fun provideApiService(): ApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideUserDao(appDatabase: AppDatabase): UserDao {
        return appDatabase.userDao()
    }

    // Comment to get compilation error
    @Provides
    @Singleton
    fun provideUserRemoteDataSource(apiService: ApiService): UserRemoteDataSource {
        return UserRemoteDataSource(apiService)
    }

    // Comment to get compilation error
    @Provides
    @Singleton
    fun provideUserRepository(
        userDao: UserDao,
        userRemoteDataSource: UserRemoteDataSource
    ): IUserRepository {
        return UserRepository(userDao, userRemoteDataSource)
    }
}
