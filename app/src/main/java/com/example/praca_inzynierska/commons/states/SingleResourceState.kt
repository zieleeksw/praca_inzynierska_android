package com.example.praca_inzynierska.commons.states

data class SingleResourceState<T>(
    val loading: Boolean = true,
    val resource: T? = null,
    val error: String? = null
)
