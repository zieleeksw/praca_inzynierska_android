package com.example.praca_inzynierska.training.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
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
            modifier = Modifier
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            ResourceStateHandler(
                resourceState = viewModel.userExercisesState.value,
                content = {
                    HandleExerciseScreenContent(
                        name = name,
                        repsText = viewModel.exerciseState.repetition,
                        weightText = viewModel.exerciseState.weight,
                        { reps -> viewModel.onRepetitionChanged(reps) },
                        { weight -> viewModel.onWeightChanged(weight) },
                        viewModel.exerciseState.repetitionError,
                        viewModel.exerciseState.weightError,
                        viewModel.userExercisesState.value.list,
                        { exerciseId -> viewModel.deleteExercise(exerciseId, name, date) },
                        { viewModel.addExercise(date, name) },
                        { viewModel.onClear() }
                    )
                }
            )
        }
    }
}