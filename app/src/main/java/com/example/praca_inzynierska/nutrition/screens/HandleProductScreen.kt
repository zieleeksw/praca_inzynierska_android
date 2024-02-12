package com.example.praca_inzynierska.nutrition.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.resource_loaders.SingleResourceStateHandler
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.nutrition.composables.handle_product.HandleProductScreenContent
import com.example.praca_inzynierska.nutrition.vm.HandleFoodScreenViewModel
import com.example.praca_inzynierska.screens.Screens

@Composable
fun HandleProductScreen(
    navController: NavHostController,
    date: String,
    meal: String,
    foodName: String
) {

    val viewModel = viewModel<HandleFoodScreenViewModel>()

    LaunchedEffect(true) {
        viewModel.fetchFoodByName(foodName)
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(text = "Find and add Food!") { navController.navigate(Screens.FoodScreen.name) }
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = colorResource(id = R.color.light_gray))
                .padding(it)
                .padding(vertical = 8.dp, horizontal = 16.dp)
        ) {

            SingleResourceStateHandler(
                resourceState = viewModel.foodByNameState.value,
                content = { HandleProductScreenContent(navController, date, meal, viewModel) }
            )
        }
    }
}