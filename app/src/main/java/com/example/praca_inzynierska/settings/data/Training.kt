package com.example.praca_inzynierska.settings.data

import com.example.praca_inzynierska.training.data.Exercise


data class Training(
    val id: Long,
    val name: String,
    val exercises: List<Exercise>
)
