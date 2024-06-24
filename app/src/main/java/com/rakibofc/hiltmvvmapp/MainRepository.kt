package com.rakibofc.hiltmvvmapp

import javax.inject.Inject

class MainRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun getContactInfo(): ContactInfoResponse {
        return apiService.getContactInfo()
    }
}