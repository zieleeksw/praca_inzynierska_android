package com.example.praca_inzynierska.settings.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.settings.components.handle_exercise.CustomOutlinedButton


@Composable
fun SettingsScreen(
    navController: NavHostController
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.align(Alignment.Center)
        ) {
            CustomOutlinedButton(text = "Training blocks", onClick = {
                navController.navigate(Screens.TrainingBlockScreen.name)
            })
            CustomOutlinedButton(text = "Create own exercise", onClick = {
                navController.navigate(Screens.HandleUserExercisesScreen.name)
            })
            CustomOutlinedButton(text = "Create own product", onClick = {
                navController.navigate(Screens.HandleUserFoodScreen.name)
            })
            CustomOutlinedButton(text = "Body dimensions", onClick = {
                navController.navigate(Screens.BodyDimensionsScreen.name)
            })
            CustomOutlinedButton(text = "Exercise Chart", onClick = {
                navController.navigate(Screens.ExercisesChartScreen.name)
            })
            CustomOutlinedButton(text = "Food Chart", onClick = { /* Implement action */ })
        }

        Column(
            modifier = Modifier.align(Alignment.BottomCenter)
        ) {
            CustomOutlinedButton(text = "Logout", onClick = { /* Implement action */ })
        }
    }
}

@Composable
@Preview(showBackground = true)
fun SettingsScreenPreview() {
    SettingsScreen(rememberNavController())
}