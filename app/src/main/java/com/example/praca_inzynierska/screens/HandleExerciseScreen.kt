package com.example.praca_inzynierska.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.IncreaseDecreaseComponent
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.components.training.CustomButtons
import com.example.praca_inzynierska.components.training.exercise.ExercisesList
import com.example.praca_inzynierska.view.models.exercises.AddExerciseViewModel
import com.example.praca_inzynierska.view.models.exercises.DeleteExerciseViewModel
import com.example.praca_inzynierska.view.models.exercises.FetchExercisesViewModel

@Composable
fun HandleExerciseScreen(
    navController: NavHostController,
    date: String,
    name: String
) {

    val addExerciseViewModel = viewModel<AddExerciseViewModel>()
    val fetchExercisesViewModel = viewModel<FetchExercisesViewModel>()
    val deleteExerciseScreenViewModel = viewModel<DeleteExerciseViewModel>()

    LaunchedEffect(Unit) {
        fetchExercisesViewModel.fetchUserExercisesByDateAndName(date, name)
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add your reps!") { navController.popBackStack() } },
    ) {
        Column(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(it)
        ) {
            Text(
                text = name,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = colorResource(id = R.color.secondary_color))
                    .padding(vertical = 8.dp)

            )
            Column(
                modifier = Modifier.padding(horizontal = 32.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))
                IncreaseDecreaseComponent(
                    "Reps",
                    1.0, true, addExerciseViewModel.exerciseState.repetition,
                    onError = addExerciseViewModel.exerciseState.repetitionError
                ) { addExerciseViewModel.onRepetitionChanged(it) }
                Spacer(modifier = Modifier.height(4.dp))
                IncreaseDecreaseComponent(
                    "Weight (kgs)", 2.5, false,
                    addExerciseViewModel.exerciseState.weight,
                    onError = addExerciseViewModel.exerciseState.weightError
                ) { addExerciseViewModel.onWeightChanged(it) }
                Spacer(modifier = Modifier.height(4.dp))
                CustomButtons(
                    onSave = { onSave(date, name, addExerciseViewModel, fetchExercisesViewModel) },
                    onClear = { onClear(addExerciseViewModel) }
                )
                LazyColumn {
                    itemsIndexed(fetchExercisesViewModel.userExercisesState.value.list) { index, exercise ->
                        ExercisesList(
                            index = index,
                            exercise = exercise,
                            onCancelClick = {
                                onCancelClick(
                                    deleteExerciseScreenViewModel,
                                    exercise.id,
                                    fetchExercisesViewModel,
                                    date,
                                    name
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}

private fun onSave(
    date: String,
    name: String,
    addExerciseViewModel: AddExerciseViewModel,
    fetchExercisesViewModel: FetchExercisesViewModel
) {
    addExerciseViewModel.addExercise(date = date, name = name) {
        fetchExercisesViewModel.fetchUserExercisesByDateAndName(date, name)
    }
}


private fun onClear(addExerciseViewModel: AddExerciseViewModel) {
    addExerciseViewModel.onRepetitionChanged("")
    addExerciseViewModel.onWeightChanged("")
}

private fun onCancelClick(
    deleteExerciseScreenViewModel: DeleteExerciseViewModel,
    exerciseId: Long,
    fetchExercisesViewModel: FetchExercisesViewModel,
    date: String,
    name: String
) {
    deleteExerciseScreenViewModel.deleteExercise(exerciseId) {
        fetchExercisesViewModel.fetchUserExercisesByDateAndName(
            date,
            name
        )
    }
}