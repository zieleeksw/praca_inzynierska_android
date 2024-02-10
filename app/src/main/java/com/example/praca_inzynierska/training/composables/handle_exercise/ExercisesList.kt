package com.example.praca_inzynierska.training.composables.handle_exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.training.data.Exercise

@Composable
fun ExercisesList(
    index: Int,
    exercise: Exercise,
    onCancelClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp),
    ) {
        Text(
            text = "${index + 1}.",
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            fontSize = 18.sp,
            modifier = Modifier.weight(1f)
        )
        CustomText(kgs = exercise.repetition.toString(), "reps", Modifier.weight(1f))
        CustomText(kgs = exercise.weight.toString(), "kgs", Modifier.weight(1f))
        IconButton(
            onClick = onCancelClick,
            modifier = Modifier
                .background(color = Color.Red, shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE)
                .size(24.dp)
        ) {
            Icon(
                Icons.Default.Close,
                contentDescription = "Cancel",
                tint = Color.White,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}