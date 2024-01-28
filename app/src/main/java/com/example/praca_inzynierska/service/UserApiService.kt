package com.example.praca_inzynierska.service

import com.example.praca_inzynierska.LoginRequest
import com.example.praca_inzynierska.service.Retrofit.retrofit
import com.example.praca_inzynierska.data.User
import com.example.praca_inzynierska.requests.UserNutritionConfigRequest
import com.example.praca_inzynierska.requests.UserRegisterRequest
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


val userService: UserApiService = retrofit.create(UserApiService::class.java)

interface UserApiService {

    @GET("/api/user/username/{username}")
    suspend fun isUsernameAvailable(@Path("username") username: String): Response<Boolean>

    @GET("/api/user/email/{email}")
    suspend fun isEmailAvailable(): Response<Boolean>

    @POST("/api/auth/register")
    suspend fun registerUser(@Body user: UserRegisterRequest): Response<ResponseBody>

    @POST("/api/auth/authenticate")
    suspend fun login(@Body loginRequest: LoginRequest): Response<User>

    @POST("/api/user-nutrition-config/add/{userId}")
    suspend fun addNutritionConfiguration(
        @Path("userId") userId: Long,
        @Header("Authorization") authorization: String,
        @Body nutritionRequest: UserNutritionConfigRequest
    ): Response<Any>
}

