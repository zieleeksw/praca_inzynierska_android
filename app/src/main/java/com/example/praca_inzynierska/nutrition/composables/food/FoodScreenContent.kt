package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.DateSelector
import com.example.praca_inzynierska.nutrition.enums.Meal
import com.example.praca_inzynierska.nutrition.vm.FoodScreenViewModel
import java.time.LocalDate

@Composable
fun FoodScreenContent(
    viewModel: FoodScreenViewModel,
    navController: NavHostController
) {

    DateSelector(
        viewModel.date.value,
        { viewModel.onDateChanged(LocalDate.parse(it)) }) {
        viewModel.fetchFoodByDate()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.light_gray))
    ) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.TopCenter)
        ) {
            item {
                FoodCard(
                    cardTitle = Meal.BREAKFAST.displayName,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            item {
                FoodCard(
                    cardTitle = Meal.DINNER.displayName,
                    viewModel = viewModel,
                    navController = navController
                )
            }
            item {
                FoodCard(
                    cardTitle = Meal.LUNCH.displayName,
                    viewModel = viewModel,
                    navController = navController
                )
            }
        }
        CalculatedNutritionComponent(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .height(56.dp),
            viewModel = viewModel
        )
    }
}