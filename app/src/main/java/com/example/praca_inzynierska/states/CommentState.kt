package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.data.Comment

data class CommentState(
    val loading: Boolean = true,
    val list: List<Comment> = emptyList(),
    val error: String? = null
)