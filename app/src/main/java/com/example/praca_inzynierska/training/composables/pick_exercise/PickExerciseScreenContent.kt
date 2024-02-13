package com.example.praca_inzynierska.training.composables.pick_exercise

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.SearchTextField
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.training.vm.PickExerciseScreenViewModel

@Composable
fun PickExerciseScreenContent(
    navController: NavHostController,
    viewModel: PickExerciseScreenViewModel,
    date: String
) {

    SearchTextField(viewModel.searchText.value) { viewModel.onSearchTextChanged(it) }
    LazyColumn {
        items(items = viewModel.filteredExercises.value, key = { it }) { item ->
            ExerciseColumnItem(item = item) {
                navController.navigate("${Screens.HandleExerciseScreen.name}/$date/$item")
            }
        }
    }
}
