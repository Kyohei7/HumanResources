package com.example.hr.projects

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProjectsApiService {

    @GET("project/idCompany/{id}")
    fun getAllProjectData(@Path("id") id: String?): Call<ProjectsByIdCompanyResponse>

}