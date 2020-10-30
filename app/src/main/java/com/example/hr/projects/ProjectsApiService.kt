package com.example.hr.projects

import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectsApiService {

    @GET("project/idCompany/{id}")
    suspend fun getAllProjectData(@Path("id") id: String?): ProjectsByIdCompanyResponse

}