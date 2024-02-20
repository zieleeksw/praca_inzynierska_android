package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.settings.data.Training
import com.example.praca_inzynierska.settings.requests.TrainingRequest
import com.example.praca_inzynierska.settings.services.trainingService
import com.example.praca_inzynierska.settings.states.TrainingState
import kotlinx.coroutines.launch

class TrainingBlockScreenViewModel : ViewModel() {

    private val _trainingState = mutableStateOf(ResourceState<Training>())
    val trainingState: State<ResourceState<Training>> = _trainingState
    var nameState by mutableStateOf(TrainingState())

    fun deleteUserExercise(
        trainingId: Long
    ) {
        viewModelScope.launch {
            try {
                trainingService.deleteTraining("Bearer ${Global.token}", trainingId)
                fetchTrainings()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addTraining(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                checkAndAddTraining(onSuccess)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun checkAndAddTraining(onSuccess: () -> Unit) =
        if (nameState.name.isBlank()) {
            nameState = nameState.copy(onError = "You need to add training name")
        } else {
            nameState = nameState.copy(onError = null)
            createTraining(onSuccess)
        }

    private suspend fun createTraining(onSuccess: () -> Unit) {
        val trainingRequest = TrainingRequest(nameState.name)
        try {
            val response =
                trainingService.addTraining("Bearer ${Global.token}", trainingRequest)
            if (response.isSuccessful) {
                if (response.body()!!) {
                    nameState = nameState.copy(name = "", onError = null)
                    onSuccess()
                    fetchTrainings()
                } else {
                    nameState =
                        nameState.copy(onError = "Training with that name already exists")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun fetchTrainings() {
        viewModelScope.launch {
            try {
                val response =
                    trainingService.fetchTrainings(
                        "Bearer ${Global.token}",
                        Global.currentUserId
                    )
                _trainingState.value = _trainingState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _trainingState.value = _trainingState.value.copy(
                    loading = false,
                    error = "Cannot load exercises"
                )
            }
        }
    }

    fun onNameChanged(name: String) {
        nameState = nameState.copy(name = name)
    }

    fun onDismiss() {
        nameState = TrainingState()
    }

    fun countUniqueExerciseNames(trainingId: Long): Int {
        val training = _trainingState.value.list.find { it.id == trainingId }
        return training?.exercises?.map { it.name }?.distinct()?.size ?: 0
    }
}