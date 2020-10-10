package com.example.hr.projects

import com.google.gson.annotations.SerializedName

class ProjectResponse(val success: Boolean, val message: String?, val data: List<Project>) {

    data class Project(

        @SerializedName("id")val id: String?,
        @SerializedName("name")val name: String?,
        @SerializedName("description")val description: String?,
        @SerializedName("deadline")val deadline: String?,
        @SerializedName("company")val company: String?,
        @SerializedName("photo")val photo: String?,
        @SerializedName("createAt")val createAt: String?,
        @SerializedName("updateAt")val updateAt: String?


    )


}