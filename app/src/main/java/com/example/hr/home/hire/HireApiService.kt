package com.example.hr.home.hire

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface HireApiService {

    @FormUrlEncoded
    @POST("hire")
    fun postHire(@Field("id_project") id_project: String?,
                 @Field("id_developer") id_developer: String?,
                 @Field("description") description: String? ,
                 @Field("price") price: String?,
                 @Field("status") status: String? ) : Call<PostHireResponse>


}