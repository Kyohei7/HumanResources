package com.example.hr.home

import com.google.gson.annotations.SerializedName

class DevelopersResponse (val success: String?, val message: String?, val data: List<Developer>) {

    data class Developer(

        @SerializedName("id")               val id:             String?,
        @SerializedName("photo")            val photo:          String?,
        @SerializedName("name_developer")             val name:           String?,
        @SerializedName("job")              val job:            String?,
        @SerializedName("location")         val location:       String?,
        @SerializedName("status")           val status:         String?,
        @SerializedName("description")      val description:    String?,
        @SerializedName("skill")            val skill:          String?,
        @SerializedName("email")            val email:          String?,
        @SerializedName("instagram")        val instagram:      String?,
        @SerializedName("github")           val github:         String?,
        @SerializedName("gitlab")           val gitlab:         String?,
        @SerializedName("portfolio")        val portfolio:      String?,
        @SerializedName("experience")       val experience:     String?,
        @SerializedName("createAt")         val createAt:       String?,
        @SerializedName("updateAt")         val updateAt:       String?


    )



}