package com.example.praca_inzynierska.diet_configuration.requests

data class UserNutritionConfigRequest(
    val gender: String,
    val dob: String,
    val activityLevel: String,
    val height: Int,
    val currentWeight: Double,
    val targetWeight: Double,
)
