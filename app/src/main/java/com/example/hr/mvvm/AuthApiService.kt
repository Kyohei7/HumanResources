package com.example.hr.mvvm

import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApiService {


    @FormUrlEncoded
    @POST("user/login")
    suspend fun loginRequest(@Field("email") email: String?,
                             @Field("password") password: String?) : LoginResponse

    @FormUrlEncoded
    @POST("user/register")
    suspend fun registerRequest(@Field("username") username: String?,
                                @Field("email") email: String?,
                                @Field("password") password: String?,
                                @Field("role") role: String?) : RegisterResponse

}