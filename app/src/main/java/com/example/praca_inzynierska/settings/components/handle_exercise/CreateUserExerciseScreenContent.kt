package com.example.praca_inzynierska.settings.components.handle_exercise

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.settings.vm.HandleUserExerciseScreenViewModel

@Composable
fun CreateUserExerciseScreenContent(
    viewModel: HandleUserExerciseScreenViewModel
) {
    LazyColumn {
        items(items = viewModel.userExercisesState.value.list, key = { it.id }) { userExercise ->
            UserExerciseCard(userExercise = userExercise, viewModel)
        }
    }
}