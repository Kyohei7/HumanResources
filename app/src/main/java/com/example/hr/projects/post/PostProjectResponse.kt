package com.example.hr.projects.post


import com.google.gson.annotations.SerializedName

data class PostProjectResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)