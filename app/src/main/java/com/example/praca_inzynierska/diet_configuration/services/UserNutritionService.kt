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

    @POST("/api/user-nutrition-config/{userId}/activity_level/{activity_level}")
    suspend fun changeActivityLevel(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
        @Path("activity_level") activityLevel: String
    ): Response<Void>

    @POST("/api/user-nutrition-config/{userId}/current_weight/{current_weight}")
    suspend fun updateCurrentWeight(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
        @Path("current_weight") currentWeight: Double
    ): Response<Void>

    @POST("/api/user-nutrition-config/{userId}/target_weight/{target_weight}")
    suspend fun updateTargetWeight(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
        @Path("target_weight") targetWeight: Double
    ): Response<Void>

}