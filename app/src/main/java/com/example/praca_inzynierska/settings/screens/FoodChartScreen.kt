package com.example.praca_inzynierska.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.settings.components.food_chart.FoodChartScreenContent
import com.example.praca_inzynierska.settings.vm.FoodChartViewModel

@Composable
fun FoodChartScreen(
    navController: NavHostController,
) {

    val viewModel = viewModel<FoodChartViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchFoodByYearMonth()
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Food chart") { navController.popBackStack() } }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            ResourceStateHandler(
                resourceState = viewModel.userFoodState.value,
                content = { FoodChartScreenContent(viewModel) }
            )
        }
    }
}