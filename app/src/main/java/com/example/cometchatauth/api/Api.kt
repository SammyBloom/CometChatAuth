package com.example.cometchatauth.api

import com.example.cometchatauth.models.DefaultResponse
import retrofit2.Call
import retrofit2.http.*

interface Api {

    @FormUrlEncoded
    @POST("users")
    fun createUser(
        @Header("appId") appId: String,
        @Header("apiKey") apiKey: String,
        @Field("uid") uid: String,
        @Field("name") name: String
    ):Call<DefaultResponse>
}