package com.example.praca_inzynierska.screens.training

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.components.general.DateSelector
import com.example.praca_inzynierska.components.training.ExerciseCard
import com.example.praca_inzynierska.data.ExerciseGroup
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.exercises.DeleteExerciseViewModel
import java.time.LocalDate

@Composable
fun TrainingScreenContent(
    currentDate: MutableState<LocalDate>,
    onDateSelectorArrowClick: () -> Unit,
    exercisesGroups: List<ExerciseGroup>,
    navController: NavHostController,
) {

    val deleteExerciseViewModel = viewModel<DeleteExerciseViewModel>()

    DateSelector(currentDate) { onDateSelectorArrowClick() }
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn {
        items(exercisesGroups) { exerciseGroup ->
            ExerciseCard(
                exerciseGroup = exerciseGroup,
                onExerciseGroupDelete = {
                    deleteExerciseViewModel.deleteExerciseByDateAndName(
                        currentDate.value.toString(),
                        exerciseGroup.title
                    ) { onDateSelectorArrowClick() }
                }
            ) {
                navController.navigate(
                    "${Screens.HandleExerciseScreen.name}/${currentDate.value}/${exerciseGroup.title}"
                )
            }
        }
    }
}