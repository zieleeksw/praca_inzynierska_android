package com.example.praca_inzynierska.settings.requests

import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.settings.states.UserFoodState

data class UserFoodRequest(
    val userId: Long = Global.currentUserId,
    val productName: String,
    val kcal: Int,
    val fat: Int,
    val carbs: Int,
    val proteins: Int
) {
    constructor(userFoodState: UserFoodState) : this(
        productName = userFoodState.name,
        kcal = userFoodState.kcal.toInt(),
        fat = userFoodState.fat.toInt(),
        carbs = userFoodState.carbs.toInt(),
        proteins = userFoodState.protein.toInt()
    )
}
