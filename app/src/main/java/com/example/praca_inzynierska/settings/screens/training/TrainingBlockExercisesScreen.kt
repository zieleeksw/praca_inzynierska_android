package com.example.praca_inzynierska.settings.screens.training

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.AddIconButton
import com.example.praca_inzynierska.commons.components.resource_loaders.SingleResourceStateHandler
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.settings.components.training_block_handle.TrainingBlockExercisesScreenContent
import com.example.praca_inzynierska.settings.vm.TrainingBlockExercisesScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingBlockExercisesScreen(
    navController: NavHostController,
    trainingId: Long
) {

    val viewModel = viewModel<TrainingBlockExercisesScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchTrainingById(trainingId)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Your training",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color),
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    AddIconButton {
                        navController.navigate("${Screens.PickExerciseScreen.name}/${trainingId}")
                    }
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
                    .background(color = colorResource(id = R.color.light_gray))
            ) {
                SingleResourceStateHandler(
                    resourceState = viewModel.training.value,
                    content = {
                        TrainingBlockExercisesScreenContent(
                            trainingId,
                            viewModel,
                            navController
                        )
                    }
                )
            }
        }
    )
}