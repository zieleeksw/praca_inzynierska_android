package com.example.praca_inzynierska.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.components.SearchTextField
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.view.models.BaseAppExercisesScreenViewModel

@Composable
fun ExerciseScreen(
    navController: NavHostController,
    date: String
) {

    val viewModel = viewModel<BaseAppExercisesScreenViewModel>()

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add exercises!") { navController.popBackStack() } },
    ) {

        val exercisesNames = viewModel.exercisesState.value.list.map { exercise -> exercise.name }

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchTextField(
                modifier = Modifier,
                list = exercisesNames,
                navController = navController,
                date = date
            )
        }
    }
}

@Preview
@Composable
fun ExerciseScreenPreview() {
    ExerciseScreen(rememberNavController(), "DATE")
}