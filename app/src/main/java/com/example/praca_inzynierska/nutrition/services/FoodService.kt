package com.example.praca_inzynierska.nutrition.services

import com.example.praca_inzynierska.nutrition.data.AppFoodModel
import com.example.praca_inzynierska.nutrition.data.Food
import com.example.praca_inzynierska.nutrition.requests.FoodRequest
import com.example.praca_inzynierska.commons.services.Retrofit
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


val foodService: FoodService = Retrofit.retrofit.create(FoodService::class.java)

interface FoodService {

    @GET("/api/base_app_food")
    suspend fun fetchAllBaseAppFood(
        @Header("Authorization") authorization: String
    ): List<AppFoodModel>

    @GET("/api/base_app_food/{name}")
    suspend fun fetchBaseAppFoodByName(
        @Header("Authorization") authorization: String,
        @Path("name") userId: String,
    ): Response<AppFoodModel>

    @POST("/api/food")
    suspend fun addFood(
        @Header("Authorization") authorization: String,
        @Body foodRequest: FoodRequest
    ): Response<Void>

    @GET("/api/food/user/{id}/date/{date}")
    suspend fun fetchFoodByDate(
        @Header("Authorization") authorization: String,
        @Path("id") userId: Long,
        @Path("date") date: String
    ): List<Food>

    @DELETE("/api/food/{id}")
    suspend fun deleteFood(
        @Header("Authorization") authorization: String,
        @Path("id") foodId: Long
    ): Response<Void>
}