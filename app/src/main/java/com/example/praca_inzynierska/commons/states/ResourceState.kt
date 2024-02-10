package com.example.praca_inzynierska.commons.states

data class ResourceState<T>(
    val loading: Boolean = true,
    val list: List<T> = emptyList(),
    val error: String? = null
)