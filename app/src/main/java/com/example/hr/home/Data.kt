package com.example.hr.home


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city")
    val city: String,
    @SerializedName("createAt")
    val createAt: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("id_company")
    val idCompany: String,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("instagram")
    val instagram: String,
    @SerializedName("linkedin")
    val linkedin: String,
    @SerializedName("name_company")
    val nameCompany: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("sector")
    val sector: String,
    @SerializedName("updateAt")
    val updateAt: String
)