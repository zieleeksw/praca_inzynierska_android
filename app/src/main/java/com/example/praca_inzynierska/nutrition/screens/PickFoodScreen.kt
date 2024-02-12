package com.example.praca_inzynierska.nutrition.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.nutrition.composables.pick_food.PickFoodScreenContent
import com.example.praca_inzynierska.nutrition.vm.PickFoodScreenViewModel

@Composable
fun PickFoodScreen(
    navController: NavHostController,
    date: String,
    meal: String
) {

    val viewModel = viewModel<PickFoodScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchAllBaseAppFood()
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add food!") { navController.popBackStack() } },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResourceStateHandler(
                resourceState = viewModel.foodState.value,
                content = { PickFoodScreenContent(viewModel, navController, date, meal) }
            )
        }
    }
}
