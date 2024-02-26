package com.example.praca_inzynierska.commons.services

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Retrofit {
    val retrofit: Retrofit = Retrofit.Builder().baseUrl("https://praca-inzynierska-backend.onrender.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}