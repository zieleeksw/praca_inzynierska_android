package com.example.praca_inzynierska.api_service

import com.example.praca_inzynierska.data.Food
import com.example.praca_inzynierska.requests.FoodRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path


val foodApiService = Retrofit.retrofit.create(FoodApiService::class.java)

interface FoodApiService {

    @POST("/api/food")
    suspend fun addFood(
        @Header("Authorization") authorization: String,
        @Body foodRequest: FoodRequest
    ): Response<Void>

    @GET("/api/food/user/{id}/date/{date}")
    suspend fun fetchFoodByDate(
        @Header("Authorization") authorization: String,
        @Path("id") userId: Long,
        @Path ("date") date : String
    ): List<Food>

    @DELETE("/api/food/{id}")
    suspend fun deleteFood(
        @Header("Authorization") authorization: String,
        @Path("id") foodId: Long
    ): Response<Void>
}