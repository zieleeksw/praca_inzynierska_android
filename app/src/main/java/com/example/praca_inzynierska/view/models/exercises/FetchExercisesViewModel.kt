package com.example.praca_inzynierska.view.models.exercises

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.data.ExerciseGroup
import com.example.praca_inzynierska.data.training.Exercise
import com.example.praca_inzynierska.service.exercisesApiService
import com.example.praca_inzynierska.states.ResourceState
import kotlinx.coroutines.launch

class FetchExercisesViewModel : ViewModel() {

    private val _userExercisesState = mutableStateOf(ResourceState<Exercise>())
    val userExercisesState: State<ResourceState<Exercise>> = _userExercisesState

    fun fetchUserExercisesByDate(date: String) {
        viewModelScope.launch {
            try {
                val response =
                    exercisesApiService.fetchUserExercisesByDate(
                        "Bearer ${Global.token}",
                        Global.currentUserId,
                        date
                    )
                _userExercisesState.value = _userExercisesState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _userExercisesState.value = _userExercisesState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }

    fun fetchUserExercisesByDateAndName(
        date: String,
        name: String
    ) {
        viewModelScope.launch {
            try {
                val response =
                    exercisesApiService.fetchExercisesByDateAndName(
                        "Bearer ${Global.token}",
                        Global.currentUserId,
                        date,
                        name
                    )
                _userExercisesState.value = _userExercisesState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )

            } catch (e: Exception) {
                _userExercisesState.value = _userExercisesState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }

    fun getExercisesGroups(): List<ExerciseGroup> {
        val exercises = _userExercisesState.value.list
        return exercises.groupBy { it.name }
            .map { (name, exercises) -> ExerciseGroup(title = name, exercises = exercises) }
            .sortedBy { it.title }
    }
}
