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
import com.example.praca_inzynierska.training.vm.HandleExerciseScreenViewModel

@Composable
fun HandleExerciseScreenContent(
    viewModel: HandleExerciseScreenViewModel,
    date: String,
    name: String
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
        modifier = Modifier.padding(horizontal = 32.dp)
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        IncreaseDecreaseComponent(
            "Reps",
            1.0, true, viewModel.exerciseState.repetition,
            onError = viewModel.exerciseState.repetitionError
        ) { reps -> viewModel.onRepetitionChanged(reps) }
        Spacer(modifier = Modifier.height(4.dp))
        IncreaseDecreaseComponent(
            "Weight (kgs)", 2.5, false,
            viewModel.exerciseState.weight,
            onError = viewModel.exerciseState.weightError
        ) { viewModel.onWeightChanged(it) }
        Spacer(modifier = Modifier.height(4.dp))
        CustomButtons(
            onSave = { viewModel.addExercise(date, name) },
            onClear = { viewModel.onClear() }
        )
        LazyColumn {
            itemsIndexed(viewModel.userExercisesState.value.list) { index, exercise ->
                ExercisesList(
                    index = index,
                    exercise = exercise,
                    onCancelClick = {
                        viewModel.deleteExercise(exercise.id, name, date)
                    }
                )
            }
        }
    }
}