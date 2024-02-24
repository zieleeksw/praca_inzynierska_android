package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.nutrition.data.NutritionItem

@Composable
fun NutritionRow(
    nutritionValues: List<NutritionItem>,
    modifier: Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        nutritionValues.forEach { value ->
            NutritionProgressBar(
                title = value.title,
                currentValue = value.currentValue,
                targetValue = value.targetValue,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}