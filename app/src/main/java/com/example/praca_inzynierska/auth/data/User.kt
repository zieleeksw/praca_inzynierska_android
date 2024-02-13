package com.example.praca_inzynierska.auth.data

import com.example.praca_inzynierska.diet_configuration.data.UserNutritionConfig

data class User(
    val id: Long,
    val username: String,
    val email: String,
    val token: String,
    val userNutritionConfig: UserNutritionConfig?
)
