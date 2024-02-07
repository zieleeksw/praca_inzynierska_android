package com.example.praca_inzynierska.view.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.service.exercisesApiService
import com.example.praca_inzynierska.states.BaseAppExercisesState
import kotlinx.coroutines.launch

class BaseAppExercisesScreenViewModel : ViewModel() {

    private val _exercisesState = mutableStateOf(BaseAppExercisesState())
    val exercisesState: State<BaseAppExercisesState> = _exercisesState

    init {
        fetchAllBaseAppExercises()
    }

    private fun fetchAllBaseAppExercises() {
        viewModelScope.launch {
            try {
                val response = exercisesApiService.fetchAllBaseAppExercises("Bearer ${Global.token}")
                _exercisesState.value = _exercisesState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _exercisesState.value = _exercisesState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }
}