package com.example.praca_inzynierska.settings.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.nutrition.data.AppFoodModel
import com.example.praca_inzynierska.settings.requests.UserFoodRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path

val userFoodService: UserFoodService =
    Retrofit.retrofit.create(UserFoodService::class.java)

interface UserFoodService {

    @GET("/api/user/{userId}/food")
    suspend fun fetchUserFood(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
    ): List<AppFoodModel>

    @POST("/api/user/food")
    suspend fun addUserFood(
        @Header("Authorization") authorization: String,
        @Body userFoodRequest: UserFoodRequest
    ): Response<Boolean>

    @DELETE("/api/user/{userId}/food/{foodName}")
    suspend fun deleteUserFoodByName(
        @Header("Authorization") authorization: String,
        @Path("userId") userId: Long,
        @Path("foodName") foodName: String
    ): Response<Void>
}