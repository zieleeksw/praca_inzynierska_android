package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.data.training.BaseAppExercises

data class BaseAppExercisesState(
    val loading: Boolean = true,
    val list: List<BaseAppExercises> = emptyList(),
    val error: String? = null
)
