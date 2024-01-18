package com.example.praca_inzynierska.data

data class Food(
    val id: Long,
    val meal: String,
    val date: String,
    val grams: String,
    val productName: String,
    val kcal: Int,
    val fat: Int,
    val carbs: Int,
    val proteins: Int
)
