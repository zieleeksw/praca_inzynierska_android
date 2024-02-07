package com.example.praca_inzynierska.view.models.exercises

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.service.exercisesApiService
import kotlinx.coroutines.launch

class DeleteExerciseViewModel : ViewModel() {

    fun deleteExercise(
        exerciseId: Long,
        afterDelete: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response =
                    exercisesApiService.deleteExercise("Bearer ${Global.token}", exerciseId)
                if (response.isSuccessful) {
                    afterDelete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteExerciseByDateAndName(
        date: String,
        name: String,
        afterDelete: () -> Unit
    ) {
        viewModelScope.launch {
            try {
                val response =
                    exercisesApiService.deleteExercisesByDateAndName(
                        "Bearer ${Global.token}",
                        date,
                        name
                    )
                if (response.isSuccessful) {
                    afterDelete()
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}