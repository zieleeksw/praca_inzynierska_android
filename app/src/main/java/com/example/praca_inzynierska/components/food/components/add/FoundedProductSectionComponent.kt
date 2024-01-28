package com.example.praca_inzynierska.components.food.components.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.requests.NutritionRequest

@Composable
fun FoundedProductSectionComponent(
    nutritionRequests: List<NutritionRequest>,
    date: String,
    meal: String
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        LazyColumn {
            items(
                items = nutritionRequests,
                key = { nutritionRequest -> nutritionRequest.name }
            ) { nutritionRequest ->
                FoundedProductItemComponent(
                    nutritionRequest,
                    date,
                    meal
                )
            }
        }
    }
}