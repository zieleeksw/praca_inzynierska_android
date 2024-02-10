package com.example.praca_inzynierska.training.composables.training

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.commons.components.DeleteDialog
import com.example.praca_inzynierska.commons.components.SecondaryColorDivider
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.training.data.ExerciseGroup
import com.example.praca_inzynierska.training.vm.TrainingScreenViewModel

@Composable
fun ExerciseCard(
    viewModel: TrainingScreenViewModel,
    exerciseGroup: ExerciseGroup,
    onCardClick: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = "Exercise",
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                viewModel.deleteExerciseByDateAndName(exerciseGroup.title)
                showDialog = false
            })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = Ui.DEFAULT_CARD_ELEVATION),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(text = exerciseGroup.title, fontWeight = FontWeight.Bold)
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Close, contentDescription = "Delete")
                }
            }
            SecondaryColorDivider()
            Spacer(modifier = Modifier.height(6.dp))
            exerciseGroup.exercises.forEach { exercise ->
                Row(
                    horizontalArrangement = Arrangement.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                ) {
                    Text(text = "${exercise.repetition} reps ", modifier = Modifier.width(64.dp))
                    Spacer(modifier = Modifier.width(64.dp))
                    Text(text = "${exercise.weight} kg")
                }
            }
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}
