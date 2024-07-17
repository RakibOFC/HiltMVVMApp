package com.rakibofc.hiltmvvmapp.data.remote

import com.rakibofc.hiltmvvmapp.domain.model.ApiResponse
import com.rakibofc.hiltmvvmapp.domain.model.UserResponse
import retrofit2.http.GET

interface ApiService {

    @GET("users")
    suspend fun getUsers(): ApiResponse<List<UserResponse>>
}