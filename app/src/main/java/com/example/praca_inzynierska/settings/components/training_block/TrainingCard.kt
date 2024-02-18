package com.example.praca_inzynierska.settings.components.training_block

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.DeleteDialog
import com.example.praca_inzynierska.commons.components.SecondaryColorDivider
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.settings.data.Training
import com.example.praca_inzynierska.settings.vm.TrainingBlockScreenViewModel

@Composable
fun TrainingCard(
    training: Training,
    viewModel: TrainingBlockScreenViewModel,
    navController: NavHostController
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = "training",
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                viewModel.deleteUserExercise(training.id)
                showDialog = false
            })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { navController.navigate("${Screens.TrainingBlockExercisesScreen.name}/${training.id}") },
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = Ui.DEFAULT_CARD_ELEVATION
        ),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(
                    training.name,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Close, contentDescription = "Delete")
                }
            }
            SecondaryColorDivider()
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, end = 24.dp, start = 8.dp),
            ) {
                Text(
                    "Total exercises: ",
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Text("${viewModel.countUniqueExerciseNames(training.id)}", color = Color.Gray)
            }
        }
    }
}
