package com.example.praca_inzynierska.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("http://192.168.1.8:8080")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val foodRetrofit: Retrofit = Retrofit.Builder().baseUrl("https://api.api-ninjas.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}