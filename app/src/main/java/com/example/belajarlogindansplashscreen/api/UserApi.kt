package com.example.belajarlogindansplashscreen.api

import com.example.belajarlogindansplashscreen.api.data.LoginResponse
import com.example.belajarlogindansplashscreen.api.data.Users
import com.google.gson.JsonObject
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface UserApi {
    @POST("/auth/v1/signup")
    suspend fun signUp(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Body data : Users
    ) : Response<Unit>

    @POST("/auth/v1/token?grant_type=password")
    suspend fun signIn(
        @Header("Authorization") token: String,
        @Header("apikey") apiKey: String,
        @Body data : Users
    ) : Response<JsonObject>
}