package com.example.praca_inzynierska.training.composables.training

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.DateSelector
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.training.vm.TrainingScreenViewModel
import java.time.LocalDate

@Composable
fun TrainingScreenContent(
    viewModel: TrainingScreenViewModel,
    navController: NavHostController,
) {

    DateSelector(viewModel.date.value, { viewModel.onDateChanged(LocalDate.parse(it)) }) {
        viewModel.fetchUserExercisesByDate()
    }
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn {
        items(viewModel.getExercisesGroups()) { exerciseGroup ->
            ExerciseCard(
                viewModel = viewModel,
                exerciseGroup = exerciseGroup,
                onCardClick = {
                    navController.navigate("${Screens.HandleExerciseScreen.name}/${viewModel.date.value}/${exerciseGroup.title}")
                }
            )
        }
    }
}