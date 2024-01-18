package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.data.Food


data class FoodState(
    val loading: Boolean = true,
    val list: List<Food> = emptyList(),
    val error: String? = null
)

