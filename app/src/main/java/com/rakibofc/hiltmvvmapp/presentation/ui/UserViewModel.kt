package com.rakibofc.hiltmvvmapp.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rakibofc.hiltmvvmapp.data.remote.ApiService
import com.rakibofc.hiltmvvmapp.domain.model.User
import com.rakibofc.hiltmvvmapp.presentation.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: ApiService.IUserRepository
) : ViewModel() {

    private val _users = MutableLiveData<Resource<List<User>>>()
    val users: LiveData<Resource<List<User>>> get() = _users

    init {
        fetchUsers()
    }

    private fun fetchUsers() {
        viewModelScope.launch {
            _users.postValue(Resource.Loading())
            _users.postValue(userRepository.getUsers())
        }
    }
}
