package com.example.praca_inzynierska.training.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.training.data.Exercise
import com.example.praca_inzynierska.training.data.ExerciseGroup
import com.example.praca_inzynierska.training.service.exercisesService
import kotlinx.coroutines.launch
import java.time.LocalDate

class TrainingScreenViewModel : ViewModel() {

    private val _userExercisesState = mutableStateOf(ResourceState<Exercise>())
    val userExercisesState: State<ResourceState<Exercise>> = _userExercisesState

    private val _date = mutableStateOf(LocalDate.now())
    val date: State<LocalDate> = _date

    fun fetchUserExercisesByDate() {
        viewModelScope.launch {
            try {
                val response = exercisesService.fetchUserExercisesByDate(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    _date.value.toString()
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

    fun deleteExerciseByDateAndName(name: String) {
        viewModelScope.launch {
            try {
                val response = exercisesService.deleteExercisesByDateAndName(
                    "Bearer ${Global.token}",
                    date.value.toString(),
                    name
                )
                if (response.isSuccessful) {
                    fetchUserExercisesByDate()
                }
            } catch (e: Exception) {
                ExerciseViewModelUtils.handleException("Error deleting exercises", e) { newState ->
                    _userExercisesState.value = newState
                }
            }
        }
    }

    fun onDateChanged(newDate: LocalDate) {
        _date.value = newDate
    }

    fun getExercisesGroups(): List<ExerciseGroup> {
        val exercises = _userExercisesState.value.list
        return exercises.groupBy { it.name }
            .map { (name, exercises) -> ExerciseGroup(title = name, exercises = exercises) }
            .sortedBy { it.title }
    }
}