package com.example.hr.projects.edit


import com.google.gson.annotations.SerializedName

data class ProjectByIdResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("message")
    val message: String,
    @SerializedName("success")
    val success: Boolean
)