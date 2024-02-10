package com.example.praca_inzynierska.training.vm

import com.example.praca_inzynierska.commons.states.ResourceState


object ExerciseViewModelUtils {
    fun <T> updateExerciseState(
        response: List<T>,
        setState: (ResourceState<T>) -> Unit
    ) {
        setState(
            ResourceState(
                list = response,
                loading = false,
                error = null
            )
        )
    }

    fun <T> handleException(
        message: String,
        exception: Exception,
        setState: (ResourceState<T>) -> Unit
    ) {
        setState(
            ResourceState(
                loading = false,
                error = "$message ${exception.message}"
            )
        )
    }
}