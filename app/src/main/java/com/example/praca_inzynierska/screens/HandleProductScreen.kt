package com.example.praca_inzynierska.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.food.components.add.FoundedProductItemComponent
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.view.models.food.FetchFoodViewModel

@Composable
fun HandleProductScreen(
    navController: NavHostController,
    date: String,
    meal: String,
    foodName: String
) {

    val viewModel = viewModel<FetchFoodViewModel>()

    LaunchedEffect(true) {
        viewModel.fetchFood(foodName)
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
            FoundedProductItemComponent(
                navController,
                date,
                meal,
                viewModel.state.appFoodModel
            )
        }
    }
}

@Composable
@Preview
fun AddProductPreview() {
    HandleProductScreen(navController = rememberNavController(), "", "", "")
}