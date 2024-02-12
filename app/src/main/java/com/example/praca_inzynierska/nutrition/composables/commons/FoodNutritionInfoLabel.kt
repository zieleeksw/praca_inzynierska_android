package com.example.praca_inzynierska.nutrition.composables.commons

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.widthIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FoodNutritionInfoLabel(
    kcal: String,
    protein: String,
    carbs: String,
    fat: String
) {
    Row(
        modifier = Modifier.widthIn(260.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        NutritionText("Kcal", "$kcal kcal")
        NutritionText("P", protein)
        NutritionText("C", carbs)
        NutritionText("F", fat)
    }
}