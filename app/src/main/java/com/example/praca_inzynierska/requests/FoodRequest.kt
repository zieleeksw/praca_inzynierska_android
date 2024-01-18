package com.example.praca_inzynierska.requests

import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.states.AddFoodState

data class FoodRequest(
    val authorId: Long = Global.currentUserId,
    val meal: String,
    val date: String,
    val grams: String = "",
    val productName: String = "",
    val kcal: Int = 0,
    val fat: Int = 0,
    val carbs: Int = 0,
    val proteins: Int = 0
) {
    constructor(
        food: AddFoodState
    ) : this(
        Global.currentUserId,
        food.meal,
        food.date,
        food.grams,
        food.productName,
        food.kcal.toInt(),
        food.fat.toInt(),
        food.carbs.toInt(),
        food.proteins.toInt()
    )
}

