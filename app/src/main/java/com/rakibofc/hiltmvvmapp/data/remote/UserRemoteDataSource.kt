package com.rakibofc.hiltmvvmapp.data.remote

import javax.inject.Inject

class UserRemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun getUsers() = apiService.getUsers()
}
