package com.example.praca_inzynierska.states

data class ExerciseState(
    val repetition: String = "",
    val repetitionError: String? = null,
    val weight: String = "",
    val weightError: String? = null,
)
