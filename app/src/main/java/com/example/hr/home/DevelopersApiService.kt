package com.example.hr.home


import com.example.hr.profile.CompanyByIdUserResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface DevelopersApiService {

    @GET("developer")
    suspend fun getAllDevelopers(): DevelopersResponse

    @GET("company/{id}")
    suspend fun getCompanyId(@Path("id") id: String?) : CompanyByIdUserResponse



}