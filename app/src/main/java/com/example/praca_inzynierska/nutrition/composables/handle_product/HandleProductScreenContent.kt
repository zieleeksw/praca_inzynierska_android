package com.example.praca_inzynierska.nutrition.composables.handle_product

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.ErrorTextComponent
import com.example.praca_inzynierska.nutrition.vm.HandleFoodScreenViewModel

@Composable
fun HandleProductScreenContent(
    navController: NavHostController,
    date: String,
    meal: String,
    viewModel: HandleFoodScreenViewModel
) {

    LaunchedEffect(viewModel.addState) {
        val state = viewModel.addState.isSuccessful
        if (state) {
            navController.navigate("FoodScreen")
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .background(color = Color.White)
        ) {
            GramsTextFieldComponent(viewModel = viewModel)
            ErrorTextComponent(viewModel.foodByNameState.value.error, 0, 0)
            Spacer(modifier = Modifier.height(8.dp))
            FoodDescriptionValueComponent("Name", viewModel.addState.productName)
            FoodDescriptionValueComponent("Kcal", viewModel.addState.kcal)
            FoodDescriptionValueComponent("Fat", viewModel.addState.fat)
            FoodDescriptionValueComponent("Carbs", viewModel.addState.carbs)
            FoodDescriptionValueComponent("Proteins", viewModel.addState.proteins)
            AddFoodButton { viewModel.onSubmit(date, meal) }
        }
    }
}