package com.example.hr.home.hire


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("id_developer")
    val idDeveloper: String,
    @SerializedName("id_project")
    val idProject: String,
    @SerializedName("price")
    val price: String,
    @SerializedName("status")
    val status: String
)