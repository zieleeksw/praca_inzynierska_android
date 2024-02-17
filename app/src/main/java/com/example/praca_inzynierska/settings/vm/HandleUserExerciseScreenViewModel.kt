package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global.currentUserId
import com.example.praca_inzynierska.commons.objects.Global.token
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.settings.data.UserExercise
import com.example.praca_inzynierska.settings.requests.UserExerciseRequest
import com.example.praca_inzynierska.settings.services.userExercisesService
import com.example.praca_inzynierska.settings.states.UserExerciseState
import kotlinx.coroutines.launch

class HandleUserExerciseScreenViewModel : ViewModel() {

    private val _userExercisesState = mutableStateOf(ResourceState<UserExercise>())
    val userExercisesState: State<ResourceState<UserExercise>> = _userExercisesState
    var exerciseState by mutableStateOf(UserExerciseState())

    fun deleteUserExercise(
        userExerciseId: Long
    ) {
        viewModelScope.launch {
            try {
                userExercisesService.deleteUserExercise("Bearer $token", userExerciseId)
                fetchUserExercises()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addExercise(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                checkAndAddExercise(onSuccess)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun checkAndAddExercise(onSuccess: () -> Unit) {
        if (exerciseState.name.isBlank()) {
            exerciseState = exerciseState.copy(onError = "You need to add exercise name")
        } else {
            exerciseState = exerciseState.copy(onError = null)
            addUserExercise(onSuccess)
        }
    }

    private suspend fun addUserExercise(onSuccess: () -> Unit) {
        val userExerciseRequest = UserExerciseRequest(exerciseState.name)
        try {
            val response =
                userExercisesService.addUserExercise("Bearer $token", userExerciseRequest)
            if (response.isSuccessful) {
                if (response.body()!!) {
                    exerciseState = exerciseState.copy(name = "", onError = null)
                    onSuccess()
                    fetchUserExercises()
                } else {
                    exerciseState =
                        exerciseState.copy(onError = "Exercise with that name already exists")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun fetchUserExercises() {
        viewModelScope.launch {
            try {
                val response =
                    userExercisesService.fetchUserExercises("Bearer $token", currentUserId)
                _userExercisesState.value = _userExercisesState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _userExercisesState.value = _userExercisesState.value.copy(
                    loading = false,
                    error = "Cannot load exercises"
                )
            }
        }
    }

    fun onNameChanged(name: String) {
        exerciseState = exerciseState.copy(name = name)
    }

    fun onDismiss() {
        exerciseState = exerciseState.copy(name = "", onError = null)
    }
}