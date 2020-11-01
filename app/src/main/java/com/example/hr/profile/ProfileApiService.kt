package com.example.hr.profile

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProfileApiService {

    @GET("company/{id}")
    fun getProfilebyIdUser(@Path("id") id: String?) : Call<GetProfileResponse>


}