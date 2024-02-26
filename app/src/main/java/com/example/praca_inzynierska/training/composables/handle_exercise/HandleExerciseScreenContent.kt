package com.example.praca_inzynierska.training.composables.handle_exercise

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.training.data.Exercise

@Composable
fun HandleExerciseScreenContent(
    name: String,
    repsText: String,
    weightText: String,
    onRepsChanged: (String) -> Unit,
    onWeightChanged: (String) -> Unit,
    onRepsError: String?,
    onWeightError: String?,
    exerciseList: List<Exercise>,
    onDelete: (Long) -> Unit,
    onSave: () -> Unit,
    onClear: () -> Unit
) {
    Text(
        text = name,
        color = Color.White,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.secondary_color))
            .padding(vertical = 12.dp)
    )
    Column(
        modifier = Modifier
            .padding(horizontal = 32.dp)
            .background(color = colorResource(id = R.color.light_gray))
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        IncreaseDecreaseComponent(
            "Reps",
            1.0, true, repsText,
            onError = onRepsError
        ) { onRepsChanged(it) }
        Spacer(modifier = Modifier.height(4.dp))
        IncreaseDecreaseComponent(
            "Weight (kgs)", 2.5, false,
            weightText,
            onError = onWeightError
        ) { onWeightChanged(it) }
        Spacer(modifier = Modifier.height(4.dp))
        CustomButtons(
            onSave = { onSave() },
            onClear = { onClear() }
        )
        LazyColumn(
            modifier = Modifier.background(colorResource(id = R.color.light_gray))
        ) {
            itemsIndexed(exerciseList) { index, exercise ->
                ExercisesList(
                    index = index,
                    exercise = exercise,
                    onCancelClick = {
                        onDelete(exercise.id)
                    }
                )
            }
        }
    }
}