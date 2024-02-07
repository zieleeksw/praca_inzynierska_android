package com.example.praca_inzynierska.requests

import com.example.praca_inzynierska.Global

data class ExerciseRequest(
    val userId: Long = Global.currentUserId,
    val name: String,
    val date: String,
    val repetition: Int,
    val weight: Double
)