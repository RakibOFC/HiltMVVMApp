package com.rakibofc.hiltmvvmapp.data.remote

import com.rakibofc.hiltmvvmapp.domain.model.ContactInfoResponse
import retrofit2.http.GET

interface ApiService {

    @GET("parents/contact-info/get/8801917272522")
    suspend fun getUsers(): ContactInfoResponse
}