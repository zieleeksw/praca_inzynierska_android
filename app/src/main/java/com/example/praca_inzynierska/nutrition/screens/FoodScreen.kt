package com.example.praca_inzynierska.nutrition.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.resource_loaders.CustomCircularProgressIndicator
import com.example.praca_inzynierska.commons.components.resource_loaders.OnFetchDataErrorComponent
import com.example.praca_inzynierska.nutrition.composables.food.FoodScreenContent
import com.example.praca_inzynierska.nutrition.vm.FoodScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navController: NavHostController
) {

    val viewModel = viewModel<FoodScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchNecessaryData()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Control your calories!",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color),
                    actionIconContentColor = Color.White
                )
            )
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray))
        ) {

            when {
                viewModel.foodState.value.loading
                        || viewModel.userConfigState.value.loading ->
                    CustomCircularProgressIndicator()

                viewModel.foodState.value.error != null
                        || viewModel.userConfigState.value.error != null -> {
                    OnFetchDataErrorComponent()
                }

                else -> {
                    FoodScreenContent(viewModel = viewModel, navController = navController)
                }
            }
        }
    }
}
