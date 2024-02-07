package com.example.praca_inzynierska.states

data class ResourceState<T>(
    val loading: Boolean = true,
    val list: List<T> = emptyList(),
    val error: String? = null
)