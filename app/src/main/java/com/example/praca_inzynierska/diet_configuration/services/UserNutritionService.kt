package com.example.praca_inzynierska.diet_configuration.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.diet_configuration.data.UserNutritionConfig
import com.example.praca_inzynierska.diet_configuration.requests.UserNutritionConfigRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

val userNutritionService: UserNutritionService =
    Retrofit.retrofit.create(UserNutritionService::class.java)

interface UserNutritionService {

    @POST("/api/user-nutrition-config/add/{userId}")
    suspend fun addNutritionConfiguration(
        @Path("userId") userId: Long,
        @Header("Authorization") authorization: String,
        @Body nutritionRequest: UserNutritionConfigRequest
    ): Response<Any>

    @GET("/api/user-nutrition-config/{userId}")
    suspend fun fetchNutritionConfiguration(
        @Path("userId") userId: Long,
        @Header("Authorization") authorization: String,
    ): UserNutritionConfig
}