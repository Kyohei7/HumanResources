package com.example.hr.projects

import com.example.hr.projects.edit.ProjectByIdResponse
import com.example.hr.projects.post.PostProjectResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.*

interface ProjectsApiService {

    @GET("project/idCompany/{id}")
    fun getAllProjectData(@Path("id") id: String?): Call<ProjectsByIdCompanyResponse>

    @GET("project/{id}")
    fun getProjectById(@Path("id") id: String?): Call<ProjectByIdResponse>

    // POST Data Project
    @Multipart
    @POST("project")
    fun postDataProject(
        @Part("id_company") id_company: RequestBody,
        @Part("name_project") name_project: RequestBody,
        @Part("description") description: RequestBody,
        @Part("deadline") deadline: RequestBody,
        @Part photo: MultipartBody.Part?
    ) : Call <PostProjectResponse>

    // PUT Data Project
    @Multipart
    @PUT("project/{id}")
    fun putDataProject(
        @Path("id") id: String?,
        @Part("id_company") id_company: RequestBody,
        @Part("name_project") name_project: RequestBody,
        @Part("description") description: RequestBody,
        @Part("deadline") deadline: RequestBody,
        @Part photo: MultipartBody.Part?
    ) : Call<Void>

    // DELETE Data Project
    @DELETE("project/{id}")
    fun deleteProject(@Path("id") id: String): Call<Void>




}