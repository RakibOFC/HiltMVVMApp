package com.rakibofc.hiltmvvmapp

import retrofit2.http.GET

interface ApiService {
    @GET("parents/contact-info/get/8801917272522")
    suspend fun getContactInfo(): ContactInfoResponse
}