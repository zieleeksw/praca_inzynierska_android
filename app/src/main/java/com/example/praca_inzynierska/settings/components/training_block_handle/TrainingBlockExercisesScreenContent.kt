package com.example.praca_inzynierska.settings.components.training_block_handle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.settings.vm.TrainingBlockExercisesScreenViewModel
import com.example.praca_inzynierska.training.composables.training.ExerciseCard

@Composable
fun TrainingBlockExercisesScreenContent(
    trainingId: Long,
    viewModel: TrainingBlockExercisesScreenViewModel,
    navController: NavHostController
) {

    Text(
        text = viewModel.training.value.resource!!.name,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.secondary_color))
            .padding(vertical = 12.dp)
    )
    Spacer(modifier = Modifier.height(4.dp))
    LazyColumn {
        items(viewModel.getExercisesGroups()) { exerciseGroup ->
            ExerciseCard(
                exerciseGroup = exerciseGroup,
                onCardClick = {
                    navController.navigate("${Screens.TrainingBlockHandleExerciseScreen.name}/${trainingId}/${exerciseGroup.title}")
                },
                onDelete = {
                    viewModel.deleteExerciseFromTraining(trainingId, exerciseGroup.title)
                }
            )
        }
    }
}