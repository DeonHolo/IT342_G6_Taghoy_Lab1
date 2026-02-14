package com.example.taghoymobile.api

import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @POST("auth/register")
    fun register(@Body request: Map<String, String>): Call<Map<String, String>>

    @POST("auth/login")
    fun login(@Body request: Map<String, String>): Call<Map<String, String>>

    @POST("auth/logout")
    fun logout(): Call<Map<String, String>>

    @GET("user/me")
    fun getProfile(@Header("X-User-Id") userId: String): Call<Map<String, Any>>
}
