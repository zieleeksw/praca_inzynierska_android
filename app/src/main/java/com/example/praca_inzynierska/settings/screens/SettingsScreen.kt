package com.example.praca_inzynierska.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.settings.components.handle_exercise.CustomOutlinedButton


@Composable
fun SettingsScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.light_gray))
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            CustomOutlinedButton(text = "Diet settings", onClick = {
                navController.navigate(Screens.DietSettingsScreen.name)
            })
            CustomOutlinedButton(text = "Training blocks", onClick = {
                navController.navigate(Screens.TrainingBlockScreen.name)
            })
            CustomOutlinedButton(text = "Custom exercise", onClick = {
                navController.navigate(Screens.HandleUserExercisesScreen.name)
            })
            CustomOutlinedButton(text = "Custom product", onClick = {
                navController.navigate(Screens.HandleUserFoodScreen.name)
            })
            CustomOutlinedButton(text = "Body dimensions", onClick = {
                navController.navigate(Screens.BodyDimensionsScreen.name)
            })
            CustomOutlinedButton(text = "Exercise Chart", onClick = {
                navController.navigate(Screens.ExercisesChartScreen.name)
            })
            CustomOutlinedButton(text = "Food Chart", onClick = {
                navController.navigate(Screens.FoodChartScreen.name)
            })
        }
        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            CustomOutlinedButton(text = "Logout", onClick = {
                navController.navigate(Screens.Logout.name) {
                    popUpTo(0) {
                        inclusive = true
                    }
                    launchSingleTop = true
                }
            })
        }
    }
}
