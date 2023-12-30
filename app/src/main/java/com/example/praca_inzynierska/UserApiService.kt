package com.example.praca_inzynierska

import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

val retrofit =
    Retrofit.Builder().baseUrl("http://192.168.11.144:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
val userService = retrofit.create(UserApiService::class.java)

interface UserApiService {

    @GET("/api/user/usernames")
    suspend fun getUsers(): List<String>

    @GET("/api/user/emails")
    suspend fun getEmails(): List<String>

    @POST("/api/auth/register")
    suspend fun registerUser(@Body user: User): Response<ResponseBody>
}
