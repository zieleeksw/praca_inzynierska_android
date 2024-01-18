package com.example.praca_inzynierska.data

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val token: String,
    val userNutritionConfig: UserNutritionConfig?
)
