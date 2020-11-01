package com.example.hr.home


import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersApiService {

    @GET("developer")
    fun getAllDevelopers(): Call<DevelopersResponse>

    @GET("company/{id}")
    fun getCompanyId(@Path("id") id: String?) : Call<CompanyByIdUserResponse>



}