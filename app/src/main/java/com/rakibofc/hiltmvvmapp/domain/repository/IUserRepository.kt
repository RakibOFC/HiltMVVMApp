package com.rakibofc.hiltmvvmapp.domain.repository

import com.rakibofc.hiltmvvmapp.domain.model.User
import com.rakibofc.hiltmvvmapp.presentation.util.Resource

interface IUserRepository {
    suspend fun getUsers(): Resource<List<User>>
}
