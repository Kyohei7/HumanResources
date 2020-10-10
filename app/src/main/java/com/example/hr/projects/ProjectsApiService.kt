package com.example.hr.projects

import retrofit2.http.GET

interface ProjectsApiService {

    @GET("projects")
    suspend fun getAllProjectData(): ProjectResponse


}