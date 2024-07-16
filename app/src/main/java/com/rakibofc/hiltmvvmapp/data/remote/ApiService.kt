package com.rakibofc.hiltmvvmapp.data.remote

import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import com.rakibofc.hiltmvvmapp.domain.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): List<User>

    interface IUserRepository {
        suspend fun getUsers(): Resource<List<User>>
    }
}