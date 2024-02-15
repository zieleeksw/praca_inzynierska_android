package com.example.praca_inzynierska.training.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.training.service.exercisesService
import kotlinx.coroutines.launch

class PickExerciseScreenViewModel : ViewModel() {

    private val _exercisesState = mutableStateOf(ResourceState<String>())
    val exercisesState: State<ResourceState<String>> = _exercisesState

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _filteredExercises = mutableStateOf(listOf<String>())
    val filteredExercises: State<List<String>> = _filteredExercises

    fun fetchAllBaseAppExercises() {
        viewModelScope.launch {
            _exercisesState.value = ResourceState(loading = true)
            try {
                val response = exercisesService.fetchAvailableExercises("Bearer ${Global.token}")
                _exercisesState.value = ResourceState(loading = false, list = response)
                filterExercises(_searchText.value)
            } catch (e: Exception) {
                _exercisesState.value = ResourceState(
                    loading = false, error = "Błąd pobierania ćwiczeń: ${e.message}"
                )
            }
        }
    }

    fun onSearchTextChanged(newSearchText: String) {
        _searchText.value = newSearchText
        filterExercises(newSearchText)
    }

    private fun filterExercises(filter: String) {
        _filteredExercises.value = _exercisesState.value.list.filter {
            it.contains(filter, ignoreCase = true)
        }.map { it }
    }
}