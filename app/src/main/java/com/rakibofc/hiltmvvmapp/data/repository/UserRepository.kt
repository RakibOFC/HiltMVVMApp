package com.rakibofc.hiltmvvmapp.data.repository

import com.rakibofc.hiltmvvmapp.data.local.UserDao
import com.rakibofc.hiltmvvmapp.data.local.UserEntity
import com.rakibofc.hiltmvvmapp.data.remote.UserRemoteDataSource
import com.rakibofc.hiltmvvmapp.domain.model.User
import com.rakibofc.hiltmvvmapp.domain.repository.IUserRepository
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao,
    private val userRemoteDataSource: UserRemoteDataSource
) : IUserRepository {

    override suspend fun getUsers(): Resource<List<User>> {
        return try {

            val response = userRemoteDataSource.getUsers()
            val users = mutableListOf<User>()

            response.data.forEach {
                users.add(User(it.id, "${it.firstName} ${it.lastName}", it.email, it.avatar))
            }
            userDao.insertAll(users.map { it.toEntity() })

            val localUsers = userDao.getAllUsers()
            val localUsersList = mutableListOf<User>()
            localUsers.forEach {
                localUsersList.add(User(it.id, it.name, it.email, it.avatar))
            }

            Resource.Success(localUsersList)

        } catch (exception: Exception) {
            Resource.Error("Error: ${exception.message}")
        }
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(id, name, email, avatar)
    }
}
