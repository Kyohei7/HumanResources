package com.example.hr.home


import retrofit2.http.GET

interface DevelopersApiService {

    @GET("developers")
    suspend fun getAllDevelopers(): DevelopersResponse



}