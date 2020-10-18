package com.example.hr.home


import retrofit2.http.GET

interface DevelopersApiService {

    @GET("developer")
    suspend fun getAllDevelopers(): DevelopersResponse



}