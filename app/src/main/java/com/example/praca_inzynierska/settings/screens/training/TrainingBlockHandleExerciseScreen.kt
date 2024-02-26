package com.example.praca_inzynierska.settings.screens.training

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
import com.example.praca_inzynierska.settings.vm.TrainingBlockHandleExerciseScreenViewModel
import com.example.praca_inzynierska.training.composables.handle_exercise.HandleExerciseScreenContent

@Composable
fun TrainingBlockHandleExerciseScreen(
    navController: NavHostController,
    trainingId: Long,
    name: String
) {

    val viewModel = viewModel<TrainingBlockHandleExerciseScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchExercisesByTrainingIdAndName(trainingId, name)
    }

    Scaffold(
        containerColor = colorResource(id = R.color.light_gray),
        topBar = { CustomTopAppBar(text = "Add your reps!") { navController.popBackStack() } },
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
                .background(colorResource(id = R.color.light_gray))
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
                        { exerciseId ->
                            viewModel.deleteExerciseFromTraining(
                                exerciseId,
                                name,
                                trainingId
                            )
                        },
                        { viewModel.addExerciseToTraining(name, trainingId) },
                        { viewModel.onClear() }
                    )
                }
            )
        }
    }
}