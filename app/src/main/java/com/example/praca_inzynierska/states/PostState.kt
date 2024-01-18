package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.data.Post

data class PostState(
    val loading: Boolean = true,
    val list: List<Post> = emptyList(),
    val error: String? = null
)