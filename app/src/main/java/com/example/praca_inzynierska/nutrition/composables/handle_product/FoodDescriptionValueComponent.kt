package com.example.praca_inzynierska.nutrition.composables.handle_product

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun FoodDescriptionValueComponent(
    label: String,
    value: String
) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp, end = 24.dp, start = 8.dp),
    ) {
        Text(label, color = Color.Gray)
        Text(value, color = Color.Gray)
    }
}