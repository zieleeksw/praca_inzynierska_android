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
import androidx.navigation.NavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.settings.components.exercise_chart.ExerciseChartScreenContent
import com.example.praca_inzynierska.settings.vm.ExerciseChartViewModel


@Composable
fun ExercisesChartScreen(
    navController: NavController,
    exerciseName: String?
) {

    val viewModel = viewModel<ExerciseChartViewModel>()

    LaunchedEffect(Unit) {
        viewModel.onExerciseChanged(exerciseName)
        viewModel.fetchExercisesByYearMonthAndName()
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Exercises chart") { navController.popBackStack() } }

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            ResourceStateHandler(
                resourceState = viewModel.userExercisesState.value,
                content = { ExerciseChartScreenContent(viewModel, navController) }
            )
        }
    }
}