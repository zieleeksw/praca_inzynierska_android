package com.example.praca_inzynierska.screens.training

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.general.ResourceStateHandler
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.exercises.FetchExercisesViewModel
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen(
    navController: NavHostController
) {

    val currentDate = remember { mutableStateOf(LocalDate.now()) }
    val viewModel = viewModel<FetchExercisesViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchUserExercisesByDate(currentDate.value.toString())
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Training",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color),
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = {
                        navController.navigate(
                            "${Screens.ExercisesScreen.name}/${currentDate.value}"

                        )
                    }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
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
                ResourceStateHandler(
                    resourceState = viewModel.userExercisesState.value,
                    content = {
                        TrainingScreenContent(
                            currentDate,
                            { viewModel.fetchUserExercisesByDate(currentDate.value.toString()) },
                            viewModel.getExercisesGroups(),
                            navController,
                        )
                    }
                )
            }
        }
    )
}