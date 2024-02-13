package com.example.praca_inzynierska.forum.states

data class CreatePostState(
    val content: String = "",
    val contentError: String? = null
)
