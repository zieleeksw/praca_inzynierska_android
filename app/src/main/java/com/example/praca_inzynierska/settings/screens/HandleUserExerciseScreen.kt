package com.example.praca_inzynierska.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.settings.components.commons.CreateDialog
import com.example.praca_inzynierska.settings.components.handle_exercise.CreateUserExerciseScreenContent
import com.example.praca_inzynierska.settings.vm.HandleUserExerciseScreenViewModel

@Composable
fun HandleUserExercisesScreen(
    navController: NavHostController
) {

    val viewModel = viewModel<HandleUserExerciseScreenViewModel>()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchUserExercises()
    }

    if (showDialog) {
        CreateDialog(
            value = viewModel.exerciseState.name,
            onValueChanged = { viewModel.onNameChanged(it) },
            error = viewModel.exerciseState.onError,
            onDismissRequest = {
                showDialog = false
                viewModel.onDismiss()
            },
            onAdd = {
                viewModel.addExercise {
                    showDialog = false
                    viewModel.onDismiss()
                }
            }, name = "exercise"
        )
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Custom exercise") { navController.popBackStack() } }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            ResourceStateHandler(
                resourceState = viewModel.userExercisesState.value,
                content = { CreateUserExerciseScreenContent(viewModel) }
            )
        }
    }
}