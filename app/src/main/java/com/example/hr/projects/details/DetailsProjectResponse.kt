package com.example.hr.projects.details


import com.google.gson.annotations.SerializedName

data class DetailsProjectResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)