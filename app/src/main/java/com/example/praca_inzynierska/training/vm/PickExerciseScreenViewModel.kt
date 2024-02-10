package com.example.praca_inzynierska.training.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global.token
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.training.data.BaseAppExercises
import com.example.praca_inzynierska.training.service.exercisesService
import kotlinx.coroutines.launch

class PickExerciseScreenViewModel : ViewModel() {

    private val _exercisesState = mutableStateOf(ResourceState<BaseAppExercises>())
    val exercisesState: State<ResourceState<BaseAppExercises>> = _exercisesState

    fun fetchAllBaseAppExercises() {
        viewModelScope.launch {
            _exercisesState.value = ResourceState(loading = true)
            try {
                val token = "Bearer $token"
                val response = exercisesService.fetchAllBaseAppExercises(token)
                _exercisesState.value = ResourceState(loading = false, list = response)
            } catch (e: Exception) {
                _exercisesState.value = ResourceState(
                    loading = false, error = "Błąd pobierania ćwiczeń: ${e.message}"
                )
            }
        }
    }
}