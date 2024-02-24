package com.example.praca_inzynierska.settings.services

import com.example.praca_inzynierska.commons.services.Retrofit
import com.example.praca_inzynierska.nutrition.data.Food
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

val foodChartService: FoodChartService =
    Retrofit.retrofit.create(FoodChartService::class.java)

interface FoodChartService {

    @GET("/api/food/user/{userId}/chart/{date}")
    suspend fun fetchFoodByYearMonth(
        @Header("Authorization") authorization: String,
        @Path("userId") trainingId: Long,
        @Path("date") date: String,
    ): List<Food>
}