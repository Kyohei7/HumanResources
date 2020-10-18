package com.example.hr.mvvm

import com.google.gson.annotations.SerializedName

data class LoginResponse (val success: String?, val message: String, val data: DataResult?) {

    data class DataResult(
        @SerializedName("id_user") val id: String,
        @SerializedName("user_name") val name: String,
        @SerializedName("email") val email: String?,
        @SerializedName("role") val role: String?,
        @SerializedName("token") val token: String
    )

}