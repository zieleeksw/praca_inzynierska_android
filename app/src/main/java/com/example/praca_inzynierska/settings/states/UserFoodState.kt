package com.example.praca_inzynierska.settings.states

data class UserFoodState(
    val name: String = "",
    val onError: String? = null,
    val kcal: String = "",
    val onKcalError: String? = null,
    val fat: String = "",
    val onFatError: String? = null,
    val carbs: String = "",
    val onCarbsError: String? = null,
    val protein: String = "",
    val onProteinError: String? = null,
)