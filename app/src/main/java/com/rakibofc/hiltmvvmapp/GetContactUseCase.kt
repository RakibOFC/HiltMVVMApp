package com.rakibofc.hiltmvvmapp

import javax.inject.Inject

class GetContactUseCase @Inject constructor(
    private val mainRepository: MainRepository
) {
    suspend operator fun invoke(): ContactInfoResponse {
        return mainRepository.getContactInfo()
    }
}