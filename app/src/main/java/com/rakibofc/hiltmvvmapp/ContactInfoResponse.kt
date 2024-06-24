package com.rakibofc.hiltmvvmapp

import com.google.gson.annotations.SerializedName

data class ContactInfoResponse(
    val success: Boolean,
    val statusCode: Int,
    val message: String,
    val data: ContactData
)

data class ContactData(
    val phoneNumber: String,
    val emergencyContactNumber: String,
    val whatsappNumber: String,
    val childrenInfo: List<ChildrenInfo>,
    val createdAt: String,
    val updatedAt: String
)

data class ChildrenInfo(
    val name: String?,
    val grade: String?,
    val institute: String?,
    val curriculum: String?,
    val educationVariant: String?,
    val studentGender: String?,
    @SerializedName("_id") val id: String
)