package com.example.hr.home.details


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("description")
    val description: String,
    @SerializedName("email")
    val email: String,
    @SerializedName("github")
    val github: String,
    @SerializedName("gitlab")
    val gitlab: String,
    @SerializedName("id_developer")
    val idDeveloper: String?,
    @SerializedName("id_user")
    val idUser: Int,
    @SerializedName("instagram")
    val instagram: String,
    @SerializedName("job")
    val job: String,
    @SerializedName("location")
    val location: String,
    @SerializedName("name_developer")
    val nameDeveloper: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("skill")
    val skill: String,
    @SerializedName("status")
    val status: String
)