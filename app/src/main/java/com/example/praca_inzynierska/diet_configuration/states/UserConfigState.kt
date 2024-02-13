package com.example.praca_inzynierska.diet_configuration.states

import com.example.praca_inzynierska.diet_configuration.data.UserNutritionConfig

data class UserConfigState(
    val loading: Boolean = true,
    val userNutritionConfig: UserNutritionConfig? = null,
    val error: String? = null
)