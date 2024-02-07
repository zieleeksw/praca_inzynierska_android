package com.example.praca_inzynierska.view.models.exercises

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.requests.ExerciseRequest
import com.example.praca_inzynierska.service.exercisesApiService
import com.example.praca_inzynierska.states.ExerciseState
import kotlinx.coroutines.launch

class AddExerciseViewModel : ViewModel() {

    var exerciseState by mutableStateOf(ExerciseState())

    fun addExercise(
        name: String,
        date: String,
        afterAdd: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                validateExerciseState()
                if (exerciseState.repetitionError != null || exerciseState.weightError != null) {
                    return@launch
                }
                val exerciseRequest = getExerciseRequest(date, name)
                val response =
                    exercisesApiService.addExercise("Bearer ${Global.token}", exerciseRequest)
                if (response.isSuccessful) {
                    afterAdd()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun validateExerciseState() {
        var tempState = exerciseState
        tempState = tempState.copy(repetitionError = null, weightError = null)
        if (exerciseState.repetition == "0") {
            tempState = tempState.copy(repetitionError = "Cannot add 0 series")
        } else if (exerciseState.repetition.toIntOrNull() == null) {
            tempState = tempState.copy(repetitionError = "Repetition must be a number")
        }

        if (exerciseState.weight.toDoubleOrNull() == null) {
            tempState = tempState.copy(weightError = "Weight must be a number")
        }
        exerciseState = tempState
    }

    private fun getExerciseRequest(
        date: String,
        name: String
    ): ExerciseRequest {
        return ExerciseRequest(
            name = name,
            date = date,
            repetition = exerciseState.repetition.toInt(),
            weight = exerciseState.weight.toDouble()
        )
    }

    fun onRepetitionChanged(repetition: String) {
        exerciseState = exerciseState.copy(repetition = repetition)
    }

    fun onWeightChanged(weight: String) {
        exerciseState = exerciseState.copy(weight = weight)
    }
}