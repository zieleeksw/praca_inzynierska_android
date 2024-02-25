package com.example.praca_inzynierska.settings.components.handle_exercise

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
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
import com.example.praca_inzynierska.commons.components.DeleteDialog
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.settings.data.UserExercise
import com.example.praca_inzynierska.settings.vm.HandleUserExerciseScreenViewModel


@Composable
fun UserExerciseCard(
    userExercise: UserExercise,
    viewModel: HandleUserExerciseScreenViewModel
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = "exercise",
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                viewModel.deleteUserExercise(userExercise.id)
                showDialog = false
            })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Ui.DEFAULT_CARD_ELEVATION),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = userExercise.name,
                modifier = Modifier
                    .padding(16.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start),
                style = MaterialTheme.typography.titleMedium
            )
            IconButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Close, contentDescription = "Delete", tint = Color.Gray)
            }
        }
    }
}