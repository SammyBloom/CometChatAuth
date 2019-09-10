package com.example.cometchatauth.api

import android.util.Base64
import com.google.gson.annotations.SerializedName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import javax.inject.Singleton


@Singleton
object RetrofitClient {

    @SerializedName("apiKey")
    private val apiKey:String="e9715d5eefda6b13ac0ef24daaea11541e2a87f9"

    @SerializedName("appId")
    private val appId:String= "7425cd0fd61f16"

//    private val AUTH = "Basic "+ Base64.encodeToString(
//        appId.toByteArray(), Base64.NO_WRAP)
//    private val AUTH1 = "Basic "+ Base64.encodeToString(
//        apiKey.toByteArray(), Base64.NO_WRAP)

    private const val BASE_URL = "https://api.cometchat.com/v1.8/"

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original .newBuilder()
//                .addHeader("Authorization", AUTH)
//                .addHeader("Authorization", AUTH1)
                .method(original.method, original.body)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        .connectTimeout(120, java.util.concurrent.TimeUnit.MINUTES)
        .readTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
        .writeTimeout(120, java.util.concurrent.TimeUnit.SECONDS)
        .build()

    val instance: Api by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

        retrofit.create(Api::class.java)
    }
}