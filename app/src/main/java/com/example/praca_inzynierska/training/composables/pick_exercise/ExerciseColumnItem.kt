package com.example.praca_inzynierska.training.composables.pick_exercise

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ExerciseColumnItem(item: String, onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
    ) {
        Text(
            text = item,
            modifier = Modifier.padding(10.dp),
            style = MaterialTheme.typography.bodyMedium
        )
        Divider()
    }
}