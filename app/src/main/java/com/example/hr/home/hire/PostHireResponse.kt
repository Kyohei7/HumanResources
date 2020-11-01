package com.example.hr.home.hire


import com.google.gson.annotations.SerializedName

data class PostHireResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)