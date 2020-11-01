package com.example.hr.projects.details


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("createAt")
    val createAt: String,
    @SerializedName("deadline")
    val deadline: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("id_company")
    val idCompany: Int,
    @SerializedName("id_project")
    val idProject: Int,
    @SerializedName("name_project")
    val nameProject: String,
    @SerializedName("photo")
    val photo: String,
    @SerializedName("updateAt")
    val updateAt: String
)