package com.example.praca_inzynierska.service

import com.example.praca_inzynierska.requests.NutritionRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


val foodApiServiceExt: FoodApiServiceExt =
    Retrofit.foodRetrofit.create(FoodApiServiceExt::class.java)

interface FoodApiServiceExt {

    @Headers("X-Api-Key:ppNz4WZZkuO6lxlVBoLZNw==cti4qwAfyd2oPVbi")
    @GET("/v1/nutrition")
    suspend fun fetchNutritionData(@Query("query") query: String): Response<List<NutritionRequest>>
}