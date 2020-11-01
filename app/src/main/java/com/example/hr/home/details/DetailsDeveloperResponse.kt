package com.example.hr.home.details


import com.google.gson.annotations.SerializedName

data class DetailsDeveloperResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)