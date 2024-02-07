package com.example.praca_inzynierska.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.CustomCircularProgressIndicator
import com.example.praca_inzynierska.components.OnFetchDataErrorComponent
import com.example.praca_inzynierska.components.food.components.main.ExpandableCard
import com.example.praca_inzynierska.components.food.components.main.FoodBottomBar
import com.example.praca_inzynierska.components.general.DateSelector
import com.example.praca_inzynierska.enums.Meal
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navController: NavHostController
) {

    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val foodScreenViewModel = FoodScreenViewModel(currentDate.value.toString())

    Scaffold(
        bottomBar = { FoodBottomBar(viewModel = foodScreenViewModel) },
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
        ) {

            when {
                foodScreenViewModel.foodState.value.loading
                        || foodScreenViewModel.userConfigState.value.loading ->
                    CustomCircularProgressIndicator()

                foodScreenViewModel.foodState.value.error != null
                        || foodScreenViewModel.userConfigState.value.error != null -> {
                    OnFetchDataErrorComponent()
                }

                else -> {
                    DateSelector(
                        currentDate
                    ) { foodScreenViewModel.fetchFood() }
                    ExpandableCard(
                        cardTitle = Meal.BREAKFAST.displayName,
                        foodScreenViewModel,
                        date = currentDate.value.toString(),
                        navController
                    )
                    ExpandableCard(
                        cardTitle = Meal.DINNER.displayName,
                        foodScreenViewModel,
                        date = currentDate.value.toString(),
                        navController
                    )
                    ExpandableCard(
                        cardTitle = Meal.LUNCH.displayName,
                        foodScreenViewModel,
                        date = currentDate.value.toString(),
                        navController
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun FoodScreenPreview() {
    FoodScreen(navController = rememberNavController())
}