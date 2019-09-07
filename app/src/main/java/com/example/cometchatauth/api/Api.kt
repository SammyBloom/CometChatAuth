package com.example.cometchatauth.api

import com.example.cometchatauth.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("users")
    fun createUser(
        @Field("uid") uid:String,
        @Field("name") name:String
    ):Call<DefaultResponse>
}