package com.example.praca_inzynierska.training.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.NoteAdd
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.AddIconButton
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.training.composables.training.TrainingBlockAddDialog
import com.example.praca_inzynierska.training.composables.training.TrainingScreenContent
import com.example.praca_inzynierska.training.vm.TrainingScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TrainingScreen(
    navController: NavHostController
) {

    val viewModel = viewModel<TrainingScreenViewModel>()
    var showDialog by remember { mutableStateOf(false) }
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.fetchUserExercisesByDate()
        viewModel.fetchTrainings()
    }

    if (showDialog) {
        TrainingBlockAddDialog(
            onDismiss = { showDialog = false },
            onOk = {
                viewModel.addTrainingToDay(it)
                showDialog = false
            },
            viewModel = viewModel,
        )
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
                        if (!viewModel.trainingState.value.list.isEmpty()) {
                            showDialog = true
                        } else {
                            Toast.makeText(
                                context, "No training blocks",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }) {
                        Icon(Icons.Filled.NoteAdd, contentDescription = "Add Training Block")
                    }
                    AddIconButton {
                        navController.navigate("${Screens.PickExerciseScreen.name}/${viewModel.date.value}")
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
                    content = { TrainingScreenContent(viewModel, navController) }
                )
            }
        }
    )
}

@Preview
@Composable
fun PreviewTrainingScreen() {
    TrainingScreen(navController = rememberNavController())
}