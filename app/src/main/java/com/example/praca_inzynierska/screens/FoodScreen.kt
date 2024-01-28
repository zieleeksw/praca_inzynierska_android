package com.example.praca_inzynierska.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.food.components.main.CustomDatePicker
import com.example.praca_inzynierska.components.food.components.main.DateWithIcon
import com.example.praca_inzynierska.components.food.components.main.ExpandableCard
import com.example.praca_inzynierska.components.food.components.main.FoodBottomBar
import com.example.praca_inzynierska.enums.Meal
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodScreen(
    navController: NavHostController
) {

    var dateResult by remember { mutableStateOf(convertMillisToDateString(System.currentTimeMillis())) }
    var openDialog by remember { mutableStateOf(false) }
    val foodScreenViewModel = FoodScreenViewModel(dateResult)

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
            if (openDialog) {
                CustomDatePicker(
                    onDisableDialog = { openDialog = false },
                    onPickDate = { dateResult = it }
                )
            }

            when {
                foodScreenViewModel.foodState.value.loading
                        || foodScreenViewModel.userConfigState.value.loading ->
                    CustomCircularProgressIndicator()

                foodScreenViewModel.foodState.value.error != null
                        || foodScreenViewModel.userConfigState.value.error != null -> {
                    com.example.praca_inzynierska.components.OnErrorComponent()
                }

                else -> {

                    DateWithIcon(
                        dateResult = dateResult,
                        onClick = { openDialog = true }
                    )
                    ExpandableCard(
                        cardTitle = Meal.BREAKFAST.displayName,
                        foodScreenViewModel,
                        date = dateResult,
                        navController
                    )
                    ExpandableCard(
                        cardTitle = Meal.DINNER.displayName,
                        foodScreenViewModel,
                        date = dateResult,
                        navController
                    )
                    ExpandableCard(
                        cardTitle = Meal.LUNCH.displayName,
                        foodScreenViewModel,
                        date = dateResult,
                        navController
                    )
                }
            }
        }
    }
}

fun convertMillisToDateString(dateMillis: Long): String {
    val date = Date(dateMillis)
    val format = SimpleDateFormat("yyyy-MM-dd")
    return format.format(date)
}

@Composable
@Preview(showBackground = true)
fun FoodScreenPreview() {
    FoodScreen(rememberNavController())
}