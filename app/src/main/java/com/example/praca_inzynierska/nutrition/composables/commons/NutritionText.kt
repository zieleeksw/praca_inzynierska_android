package com.example.praca_inzynierska.nutrition.composables.commons

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun NutritionText(label: String, value: String) {
    Text(
        text = "$label $value",
        style = MaterialTheme.typography.bodyMedium,
        color = Color.Gray
    )
}