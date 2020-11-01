package com.example.hr.home


import com.google.gson.annotations.SerializedName

data class CompanyByIdUserResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)