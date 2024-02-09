package com.example.praca_inzynierska.states

data class AddFoodState(
    val meal: String = "",
    val date: String = "",
    var grams: String = "100",
    val productName: String = "",
    var kcal: String = "",
    var fat: String = "",
    var carbs: String = "",
    var proteins: String = "",
    val error: String? = null,
    val isSuccessful: Boolean = false
)
