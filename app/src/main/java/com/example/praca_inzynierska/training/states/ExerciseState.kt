package com.example.praca_inzynierska.training.states

data class ExerciseState(
    val repetition: String = "",
    val repetitionError: String? = null,
    val weight: String = "",
    val weightError: String? = null,
)
