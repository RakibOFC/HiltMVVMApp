package com.rakibofc.hiltmvvmapp.domain.model

import com.google.gson.annotations.SerializedName

data class User(
    val id: Int,
    val name: String,
    val email: String,
    val avatar: String
)

data class ApiResponse<DataClass>(
    val page: Int,
    @SerializedName("per_page") val perPage: Int,
    val total: String,
    @SerializedName("total_pages") val totalPages: Int,
    val data: DataClass
)

data class UserResponse(
    val id: Int,
    @SerializedName("first_name") val firstName: String,
    @SerializedName("last_name") val lastName: String,
    val email: String,
    val avatar: String
)