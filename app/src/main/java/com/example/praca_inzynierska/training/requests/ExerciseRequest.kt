package com.example.praca_inzynierska.training.requests

import com.example.praca_inzynierska.commons.objects.Global

data class ExerciseRequest(
    val userId: Long = Global.currentUserId,
    val name: String,
    val date: String,
    val repetition: Int,
    val weight: Double
)