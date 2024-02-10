package com.example.praca_inzynierska.training.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.training.data.Exercise
import com.example.praca_inzynierska.training.requests.ExerciseRequest
import com.example.praca_inzynierska.training.service.exercisesService
import com.example.praca_inzynierska.training.states.ExerciseState
import kotlinx.coroutines.launch

class HandleExerciseScreenViewModel : ViewModel() {

    private val _userExercisesState = mutableStateOf(ResourceState<Exercise>())
    val userExercisesState: State<ResourceState<Exercise>> = _userExercisesState

    var exerciseState by mutableStateOf(ExerciseState())


    fun fetchUserExercisesByDateAndName(date: String, name: String) {
        viewModelScope.launch {
            try {
                val response = exercisesService.fetchUserExercisesByDateAndName(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    date,
                    name
                )
                ExerciseViewModelUtils.updateExerciseState(response) { newState ->
                    _userExercisesState.value = newState
                }
            } catch (e: Exception) {
                ExerciseViewModelUtils.handleException("Error fetching exercises", e) { newState ->
                    _userExercisesState.value = newState
                }
            }
        }
    }

    fun addExercise(date: String, name: String) {
        viewModelScope.launch {
            try {
                validateExerciseState()
                if (exerciseState.repetitionError != null || exerciseState.weightError != null) {
                    return@launch
                }
                val exerciseRequest =
                    getExerciseRequest(date, name)
                val response =
                    exercisesService.addExercise("Bearer ${Global.token}", exerciseRequest)
                if (response.isSuccessful) {
                    fetchUserExercisesByDateAndName(date, name)
                }
            } catch (e: Exception) {
                ExerciseViewModelUtils.handleException("Error adding exercises", e) { newState ->
                    _userExercisesState.value = newState
                }
            }
        }
    }

    fun deleteExercise(exerciseId: Long, name: String, date: String) {
        viewModelScope.launch {
            try {
                val response =
                    exercisesService.deleteExercise("Bearer ${Global.token}", exerciseId)
                if (response.isSuccessful) {
                    fetchUserExercisesByDateAndName(name, date)
                }
            } catch (e: Exception) {
                ExerciseViewModelUtils.handleException("Error deleting exercises", e) { newState ->
                    _userExercisesState.value = newState
                }
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

    private fun getExerciseRequest(date: String, name: String): ExerciseRequest {
        return ExerciseRequest(
            name = name,
            date = date,
            repetition = exerciseState.repetition.toInt(),
            weight = exerciseState.weight.toDouble()
        )
    }

    fun onClear() {
        onRepetitionChanged("")
        onWeightChanged("")
    }

    fun onRepetitionChanged(repetition: String) {
        exerciseState = exerciseState.copy(repetition = repetition)
    }

    fun onWeightChanged(weight: String) {
        exerciseState = exerciseState.copy(weight = weight)
    }
}