package com.example.praca_inzynierska.training.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.components.general.ResourceStateHandler
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.training.composables.handle_exercise.HandleExerciseScreenContent
import com.example.praca_inzynierska.training.vm.HandleExerciseScreenViewModel

@Composable
fun HandleExerciseScreen(
    navController: NavHostController,
    date: String,
    name: String
) {

    val viewModel = viewModel<HandleExerciseScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchUserExercisesByDateAndName(date, name)
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add your reps!") { navController.popBackStack() } },
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(it)
        ) {
            ResourceStateHandler(
                resourceState = viewModel.userExercisesState.value,
                content = { HandleExerciseScreenContent(viewModel, date, name) }
            )
        }
    }
}