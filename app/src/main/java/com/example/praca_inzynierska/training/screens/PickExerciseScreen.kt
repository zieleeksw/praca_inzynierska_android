package com.example.praca_inzynierska.training.screens

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
import com.example.praca_inzynierska.training.composables.pick_exercise.PickExerciseScreenContent
import com.example.praca_inzynierska.training.vm.PickExerciseScreenViewModel

@Composable
fun PickExerciseScreen(
    navController: NavHostController,
    date: String
) {

    val viewModel = viewModel<PickExerciseScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchAllBaseAppExercises()
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add exercises!") { navController.popBackStack() } },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResourceStateHandler(
                resourceState = viewModel.exercisesState.value,
                content = { PickExerciseScreenContent(navController, viewModel, date) }
            )
        }
    }
}