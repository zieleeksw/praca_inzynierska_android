package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.data.AppFoodModel

data class FoodByNameState(
    val appFoodModel: AppFoodModel = AppFoodModel(),
    val isLoading: Boolean = false,
    val error: String? = null
)