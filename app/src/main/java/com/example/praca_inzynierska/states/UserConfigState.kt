package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.data.UserNutritionConfig

data class UserConfigState(
    val loading: Boolean = true,
    val userNutritionConfig: UserNutritionConfig? = null,
    val error: String? = null
)