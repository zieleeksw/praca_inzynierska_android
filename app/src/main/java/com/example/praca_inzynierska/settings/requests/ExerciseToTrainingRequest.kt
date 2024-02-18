package com.example.praca_inzynierska.settings.requests

data class ExerciseToTrainingRequest(
    val trainingId: Long,
    val name: String,
    val repetition: Int,
    val weight: Double
)