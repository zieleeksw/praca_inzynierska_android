package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.settings.services.exercisesChartService
import com.example.praca_inzynierska.training.data.Exercise
import com.example.praca_inzynierska.training.vm.ExerciseViewModelUtils
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class ExerciseChartViewModel : ViewModel() {

    private val _userExercisesState = mutableStateOf(ResourceState<Exercise>())
    val userExercisesState: State<ResourceState<Exercise>> = _userExercisesState

    private val _date = mutableStateOf(LocalDate.now())
    val date: State<LocalDate> = _date

    var exerciseState by mutableStateOf("Pick Exercise !")

    fun onDateChanged(newDate: LocalDate) {
        _date.value = newDate
    }

    fun onExerciseChanged(name: String?) {
        exerciseState = name ?: "Pick Exercise !"
    }

    fun fetchExercisesByYearMonthAndName() {
        if (exerciseState == "Pick Exercise !") {
            _userExercisesState.value = _userExercisesState.value.copy(loading = false)
            return
        }
        _userExercisesState.value = _userExercisesState.value.copy(loading = true)
        viewModelScope.launch {
            try {
                val response = exercisesChartService.fetchExercisesByYearMonthAndName(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    formatDateToYearMonth(),
                    exerciseState
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

    fun getSortedExercises(): List<Exercise> {
        return _userExercisesState.value.list.sortedBy { exercise ->
            val dayOfMonth = exercise.date.substringAfterLast("-").toInt()
            dayOfMonth
        }
    }

    private fun formatDateToYearMonth(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        return _date.value.format(formatter)
    }

    fun getDayFromDate(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val parsedDate = LocalDate.parse(date, formatter)
        return parsedDate.dayOfMonth.toString()
    }

    fun getMaxWeight(): String {
        val maxWeight = _userExercisesState.value.list.maxOfOrNull { it.weight } ?: 0.0
        return String.format("%.1f", maxWeight)
    }

    fun getAverageWeight(): String {
        val exercises = _userExercisesState.value.list
        if (exercises.isEmpty()) return 0.0.toString()
        val averageWeight = exercises.sumOf { it.weight } / exercises.size
        return String.format("%.1f", averageWeight)
    }

    fun getAverageReps(): String {
        val exercises = _userExercisesState.value.list
        if (exercises.isEmpty()) return 0.0.toString()
        val averageReps = exercises.sumOf { it.repetition.toDouble() } / exercises.size
        return String.format("%.1f", averageReps)
    }

    fun getMaxReps(): String {
        val maxReps = _userExercisesState.value.list.maxOfOrNull { it.repetition }?.toDouble() ?: 0.0
        return String.format("%.1f", maxReps)
    }
}