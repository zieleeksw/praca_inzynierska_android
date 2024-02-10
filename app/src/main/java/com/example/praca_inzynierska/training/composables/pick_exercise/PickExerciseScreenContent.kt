package com.example.praca_inzynierska.training.composables.pick_exercise

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.SearchTextField
import com.example.praca_inzynierska.screens.Screens

@Composable
fun PickExerciseScreenContent(
    exercisesNames: List<String>,
    navController: NavHostController,
    date: String
) {

    val textState = remember { mutableStateOf(TextFieldValue("")) }

    SearchTextField(state = textState)
    LazyColumn {
        items(items = exercisesNames.filter {
            it.contains(textState.value.text, ignoreCase = true)
        }, key = { it }) { item ->
            ExerciseColumnItem(
                item = item
            ) { navController.navigate("${Screens.HandleExerciseScreen.name}/${date}/${item}") }
        }
    }
}
