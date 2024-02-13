package com.example.praca_inzynierska.auth.services

import com.example.praca_inzynierska.auth.requests.LoginRequest
import com.example.praca_inzynierska.auth.data.User
import com.example.praca_inzynierska.auth.requests.UserRegisterRequest
import com.example.praca_inzynierska.commons.services.Retrofit.retrofit
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path


val userService: UserService = retrofit.create(UserService::class.java)


interface UserService {

    @GET("/api/user/username/{username}")
    suspend fun isUsernameAvailable(@Path("username") username: String): Response<Boolean>

    @GET("/api/user/email/{email}")
    suspend fun isEmailAvailable(): Response<Boolean>

    @POST("/api/auth/register")
    suspend fun registerUser(@Body user: UserRegisterRequest): Response<ResponseBody>

    @POST("/api/auth/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): Response<User>
}

