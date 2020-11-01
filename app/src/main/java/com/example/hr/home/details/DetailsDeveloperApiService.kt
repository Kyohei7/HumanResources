package com.example.hr.home.details

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailsDeveloperApiService {

    @GET("developer/idDev/{id}")
    fun getAllDevelopersById(@Path("id") id: String?): Call<DetailsDeveloperResponse>


}