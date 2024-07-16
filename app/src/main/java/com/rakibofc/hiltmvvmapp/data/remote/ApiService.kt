package com.rakibofc.hiltmvvmapp.data.remote

import com.rakibofc.hiltmvvmapp.domain.model.ContactInfoResponse
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import com.rakibofc.hiltmvvmapp.domain.model.User
import retrofit2.http.GET

interface ApiService {

    @GET("parents/contact-info/get/8801917272522")
    suspend fun getUsers(): ContactInfoResponse

    interface IUserRepository {
        suspend fun getUsers(): Resource<List<User>>
    }
}