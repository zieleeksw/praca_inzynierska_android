package com.example.praca_inzynierska.requests

data class NutritionRequest(
    val name: String,
    val calories: Double,
    val fat_total_g: Double,
    val protein_g: Double,
    val carbohydrates_total_g: Double,
)