package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.helpers.FoodValuesCalculator
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

@Composable
fun FoodBottomBar(
    viewModel: FoodScreenViewModel
) {

    val calculator = FoodValuesCalculator()
    val foodList = viewModel.foodState.value.list

    Column(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
        ) {
            val currentKcal = calculator.calculateKcal("", foodList)
            val caloricNeeds =
                viewModel.userConfigState.value.userNutritionConfig?.caloricNeeds.toString()
            CustomProgressBar(
                "Kcal",
                currentKcal / (caloricNeeds.toFloatOrNull() ?: 0f),
                currentKcal.toString(),
                caloricNeeds
            )
            Spacer(modifier = Modifier.width(8.dp))
            val currentProtein = calculator.calculateCarbs("", foodList)
            val proteinNeeds =
                viewModel.userConfigState.value.userNutritionConfig?.proteinNeeds.toString()
            CustomProgressBar(
                "Protein",
                currentProtein / (proteinNeeds.toFloatOrNull() ?: 0f),
                currentProtein.toString(),
                proteinNeeds
            )
        }
        Row(
            modifier = Modifier.padding(horizontal = 4.dp),
        ) {
            val currentCarbs = calculator.calculateCarbs("", foodList)
            val carbsNeeds =
                viewModel.userConfigState.value.userNutritionConfig?.carbNeeds.toString()
            CustomProgressBar(
                "Carbs",
                currentCarbs / (carbsNeeds.toFloatOrNull() ?: 0f),
                currentCarbs.toString(),
                carbsNeeds
            )
            Spacer(modifier = Modifier.width(8.dp))
            val currentFat = calculator.calculateFat("", foodList)
            val fatNeeds = viewModel.userConfigState.value.userNutritionConfig?.fatNeeds.toString()

            CustomProgressBar(
                "Fat",
                currentFat / (fatNeeds.toFloatOrNull() ?: 0f),
                currentFat.toString(),
                fatNeeds
            )
        }
        Spacer(modifier = Modifier.height(6.dp))
    }
}

@Preview
@Composable
fun Previeww() {
    FoodBottomBar(FoodScreenViewModel("2022"))
}