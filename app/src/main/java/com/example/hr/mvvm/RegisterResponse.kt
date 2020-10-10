package com.example.hr.mvvm

import com.google.gson.annotations.SerializedName

data class RegisterResponse (val success: String?, val message: String?, val data: DataResult?) {

    data class DataResult(

        @SerializedName("id") val id:String?,
        @SerializedName("username") val username:String?,
        @SerializedName("email") val email:String?,
        @SerializedName("password") val password:String?,
        @SerializedName("role") val role:String?,
        @SerializedName("updateAt") val updateAt:String?,
        @SerializedName("createAt") val createAt:String?

    )


}