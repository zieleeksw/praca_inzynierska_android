package com.example.praca_inzynierska.diet_configuration.data

data class UserNutritionConfig(
    val gender: String,
    val dob: String,
    val activityLevel: String,
    val height: Int,
    val currentWeight: Double,
    val targetWeight: Double,
    val dietStatus: String,
    val caloricNeeds: Int,
    val carbNeeds: Int,
    val fatNeeds: Int,
    val proteinNeeds: Int,
    val dietFinish: String
)
