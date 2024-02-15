package com.example.praca_inzynierska.settings.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.settings.vm.CreateUserExerciseScreenViewModel

@Composable
fun CreateUserExerciseScreenContent(
    viewModel: CreateUserExerciseScreenViewModel
) {
    LazyColumn {
        items(items = viewModel.userExercisesState.value.list, key = { it.id }) { userExercise ->
            UserExerciseCard(userExercise = userExercise, viewModel)
        }
    }
}