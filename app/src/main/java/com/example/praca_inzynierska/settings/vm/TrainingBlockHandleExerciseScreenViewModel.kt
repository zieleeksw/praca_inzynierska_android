package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.settings.requests.ExerciseToTrainingRequest
import com.example.praca_inzynierska.settings.services.trainingService
import com.example.praca_inzynierska.training.data.Exercise
import com.example.praca_inzynierska.training.states.ExerciseState
import com.example.praca_inzynierska.training.vm.ExerciseViewModelUtils
import kotlinx.coroutines.launch

class TrainingBlockHandleExerciseScreenViewModel : ViewModel() {

    private val _userExercisesState = mutableStateOf(ResourceState<Exercise>())
    val userExercisesState: State<ResourceState<Exercise>> = _userExercisesState

    var exerciseState by mutableStateOf(ExerciseState())

    fun fetchExercisesByTrainingIdAndName(trainingId: Long, name: String) {
        viewModelScope.launch {
            try {
                val response = trainingService.fetchExercisesByTrainingIdAndName(
                    "Bearer ${Global.token}",
                    trainingId,
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

    fun addExerciseToTraining(name: String, trainingId: Long) {
        viewModelScope.launch {
            try {
                validateExerciseState()
                if (exerciseState.repetitionError != null || exerciseState.weightError != null) {
                    return@launch
                }
                val exerciseRequest =
                    getExerciseRequest(name, trainingId)
                val response =
                    trainingService.addExerciseToTraining("Bearer ${Global.token}", exerciseRequest)
                if (response.isSuccessful) {
                    fetchExercisesByTrainingIdAndName(trainingId, name)
                }
            } catch (e: Exception) {
                ExerciseViewModelUtils.handleException("Error adding exercises", e) { newState ->
                    _userExercisesState.value = newState
                }
            }
        }
    }

    fun deleteExerciseFromTraining(exerciseId: Long, name: String, trainingId: Long) {
        viewModelScope.launch {
            try {
                val response =
                    trainingService.deleteExerciseFromTraining("Bearer ${Global.token}", exerciseId)
                if (response.isSuccessful) {
                    fetchExercisesByTrainingIdAndName(trainingId, name)
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

    private fun getExerciseRequest(name: String, trainingId: Long): ExerciseToTrainingRequest {
        return ExerciseToTrainingRequest(
            trainingId = trainingId,
            name = name,
            repetition = exerciseState.repetition.toInt(),
            weight = exerciseState.weight.toDouble()
        )
    }
}