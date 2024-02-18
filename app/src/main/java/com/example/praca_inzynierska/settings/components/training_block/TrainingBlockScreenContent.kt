package com.example.praca_inzynierska.settings.components.training_block

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.settings.vm.TrainingBlockScreenViewModel

@Composable
fun TrainingBlockScreenContent(
    viewModel: TrainingBlockScreenViewModel,
    navController: NavHostController
) {
    LazyColumn {
        items(
            items = viewModel.trainingState.value.list,
            key = { it.name }) { training ->
            TrainingCard(training, viewModel, navController)
        }
    }
}