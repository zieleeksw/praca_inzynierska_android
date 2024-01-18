package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.requests.NutritionRequest

data class NutritionState(
    val loading: Boolean = true,
    val list: List<NutritionRequest>? = emptyList(),
    val error: String? = null
)