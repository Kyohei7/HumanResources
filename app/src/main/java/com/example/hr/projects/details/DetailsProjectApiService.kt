package com.example.hr.projects.details

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsProjectApiService {

    @GET("project/{id}")
    fun getDetailsProject(@Path("id") id: String?): Call<DetailsProjectResponse>


}