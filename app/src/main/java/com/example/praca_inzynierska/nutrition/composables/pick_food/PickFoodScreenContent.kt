package com.example.praca_inzynierska.nutrition.composables.pick_food

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.praca_inzynierska.commons.components.SearchTextField
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.nutrition.vm.PickFoodScreenViewModel

@Composable
fun PickFoodScreenContent(
    viewModel: PickFoodScreenViewModel,
    navController: NavController,
    date: String,
    meal: String
) {
    SearchTextField(value = viewModel.searchText.value) { viewModel.onSearchTextChanged(it) }
    LazyColumn {
        items(items = viewModel.filteredFood.value, key = { it.productName }) { food ->
            FoodColumnItem(
                food = food
            ) { navController.navigate("${Screens.HandleProductScreen.name}/${date}/${meal}/${food.productName}") }
        }
    }
}
