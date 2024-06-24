package com.rakibofc.hiltmvvmapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContactUseCase: GetContactUseCase
) : ViewModel() {

    private val _contactData = MutableLiveData<ContactInfoResponse>()
    val contactData: LiveData<ContactInfoResponse> get() = _contactData

    fun fetchContactData() {
        viewModelScope.launch {
            val result = getContactUseCase()
            _contactData.postValue(result)
        }
    }
}
