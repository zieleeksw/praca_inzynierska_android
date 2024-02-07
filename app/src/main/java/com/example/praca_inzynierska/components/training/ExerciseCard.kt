package com.example.praca_inzynierska.components.training

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
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
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.DeleteDialog
import com.example.praca_inzynierska.data.ExerciseGroup

@Composable
fun ExerciseCard(
    exerciseGroup: ExerciseGroup,
    onExerciseGroupDelete: () -> Unit,
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
                onExerciseGroupDelete()
                showDialog = false
            })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable { onCardClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Column {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = exerciseGroup.title, fontWeight = FontWeight.Bold)
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Close, contentDescription = "Delete")
                }
            }
            Divider(color = colorResource(id = R.color.secondary_color))
            Spacer(modifier = Modifier.height(6.dp))
            exerciseGroup.exercises.forEach { exercise ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp, vertical = 4.dp),
                    horizontalArrangement = Arrangement.Start
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
