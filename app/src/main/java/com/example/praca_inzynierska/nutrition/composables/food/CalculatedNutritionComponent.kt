package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.nutrition.data.NutritionItem
import com.example.praca_inzynierska.nutrition.vm.FoodScreenViewModel

@Composable
fun CalculatedNutritionComponent(
    modifier: Modifier = Modifier,
    viewModel: FoodScreenViewModel
) {
    val userNutritionConfig = viewModel.userConfigState.value.userNutritionConfig

    if (userNutritionConfig != null) {

        val nutritionValues = listOf(
            NutritionItem(
                "Calories",
                viewModel.calculateKcal(""),
                userNutritionConfig.caloricNeeds
            ),
            NutritionItem(
                "Protein",
                viewModel.calculateProtein(""),
                userNutritionConfig.proteinNeeds
            ),
            NutritionItem("Carbs", viewModel.calculateCarbs(""), userNutritionConfig.carbNeeds),
            NutritionItem("Fat", viewModel.calculateFat(""), userNutritionConfig.fatNeeds)
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            nutritionValues.chunked(2).forEach { rowValues ->
                NutritionRow(rowValues)
            }
        }
    }
}