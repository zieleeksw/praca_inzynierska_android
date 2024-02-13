package com.example.praca_inzynierska.forum.states

data class CreateCommentState(
    val content: String = "",
    val contentError: String? = null
)
