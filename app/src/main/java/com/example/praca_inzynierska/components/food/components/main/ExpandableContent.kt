package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.data.Food
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

@Composable
fun ExpandableContent(
    filteredFood: List<Food>,
    viewModel: FoodScreenViewModel
) {
    Column {
        for (food in filteredFood) {
            FoodCardItemComponent(food, viewModel)
        }
    }
}
