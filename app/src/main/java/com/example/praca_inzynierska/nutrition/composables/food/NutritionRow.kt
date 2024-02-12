package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.nutrition.data.NutritionItem

@Composable
fun NutritionRow(nutritionValues: List<NutritionItem>) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxWidth()
    ) {
        nutritionValues.forEach { value ->
            NutritionProgressBar(
                title = value.title,
                currentValue = value.currentValue,
                targetValue = value.targetValue
            )
        }
    }
}