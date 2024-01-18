package com.example.praca_inzynierska.api_service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit = Retrofit.Builder().baseUrl("http://192.168.1.8:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}