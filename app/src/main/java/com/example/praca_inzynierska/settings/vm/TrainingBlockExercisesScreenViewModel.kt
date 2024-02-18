package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.SingleResourceState
import com.example.praca_inzynierska.settings.data.Training
import com.example.praca_inzynierska.settings.services.trainingService
import com.example.praca_inzynierska.training.data.ExerciseGroup
import kotlinx.coroutines.launch

class TrainingBlockExercisesScreenViewModel : ViewModel() {

    private val _training = mutableStateOf(SingleResourceState<Training>())
    val training: State<SingleResourceState<Training>> = _training

    fun fetchTrainingById(trainingId: Long) {
        _training.value = _training.value.copy(loading = true, error = null)
        viewModelScope.launch {
            try {
                val response =
                    trainingService.fetchTrainingById("Bearer ${Global.token}", trainingId)
                if (response.isSuccessful && response.body() != null) {
                    _training.value = _training.value.copy(
                        resource = response.body()!!,
                        loading = false
                    )
                }
            } catch (e: Exception) {
                _training.value = _training.value.copy(
                    loading = false,
                    error = e.message ?: "An error occurred."
                )
            }
        }
    }

    fun getExercisesGroups(): List<ExerciseGroup> {
        val exercises = _training.value.resource?.exercises ?: emptyList()

        return exercises.groupBy { it.name }
            .map { (name, exercises) -> ExerciseGroup(title = name, exercises = exercises) }
            .sortedBy { it.title }
    }

    fun deleteExerciseFromTraining(trainingId: Long, exerciseName: String) {
        viewModelScope.launch {
            try {
                trainingService.deleteExerciseFromTrainingByName(
                    "Bearer ${Global.token}",
                    _training.value.resource!!.id,
                    exerciseName
                )
                fetchTrainingById(trainingId)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}