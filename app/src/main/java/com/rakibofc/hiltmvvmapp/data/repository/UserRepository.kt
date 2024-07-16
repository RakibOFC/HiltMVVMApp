package com.rakibofc.hiltmvvmapp.data.repository

import com.rakibofc.hiltmvvmapp.data.local.UserDao
import com.rakibofc.hiltmvvmapp.data.local.UserEntity
import com.rakibofc.hiltmvvmapp.data.remote.ApiService
import com.rakibofc.hiltmvvmapp.data.remote.UserRemoteDataSource
import com.rakibofc.hiltmvvmapp.domain.model.User
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userRemoteDataSource: UserRemoteDataSource
) : ApiService.IUserRepository {

    /*override suspend fun getUsers(): Resource<List<User>> {
        TODO("Not yet implemented")
    }*/

    override suspend fun getUsers(): Resource<List<User>> {
        return try {
            val response = userRemoteDataSource.getUsers()
            userDao.insertAll(response.map { it.toEntity() })
            Resource.Success(response)
        } catch (exception: Exception) {
            Resource.Error("An error occurred")
        }
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(id, name, email)
    }
}
