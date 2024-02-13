package com.example.praca_inzynierska.nutrition.requests

import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.nutrition.states.AddFoodState

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
        food: AddFoodState,
        date: String,
        meal: String,
        name: String
    ) : this(
        Global.currentUserId,
        meal,
        date,
        food.grams,
        name,
        food.kcal.toInt(),
        food.fat.toInt(),
        food.carbs.toInt(),
        food.proteins.toInt()
    )
}

