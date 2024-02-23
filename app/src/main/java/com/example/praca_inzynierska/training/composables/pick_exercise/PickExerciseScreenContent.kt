package com.example.praca_inzynierska.training.composables.pick_exercise

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.SearchTextField
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.training.vm.PickExerciseScreenViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun PickExerciseScreenContent(
    navController: NavHostController,
    viewModel: PickExerciseScreenViewModel,
    unusualKey: String
) {

    SearchTextField(viewModel.searchText.value) { viewModel.onSearchTextChanged(it) }
    LazyColumn {
        items(items = viewModel.filteredExercises.value, key = { it }) { item ->
            ExerciseColumnItem(item = item) {
                onClick(unusualKey, navController, item)
            }
        }
    }
}

fun onClick(unusualKey: String, navController: NavHostController, exerciseName: String) {
    val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)
    if (unusualKey == "CHART") {
        navController.navigate("${Screens.ExercisesChartScreen.name}/$exerciseName")
    } else {
        try {
            dateFormat.parse(unusualKey)
            navController.navigate("${Screens.HandleExerciseScreen.name}/$unusualKey/$exerciseName")
        } catch (e: Exception) {
            navController.navigate("${Screens.TrainingBlockHandleExerciseScreen.name}/$unusualKey/$exerciseName")
        }
    }
}
