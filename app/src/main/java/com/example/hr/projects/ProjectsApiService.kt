package com.example.hr.projects

import retrofit2.http.GET

interface ProjectsApiService {

    @GET("project")
    suspend fun getAllProjectData(): ProjectResponse


}