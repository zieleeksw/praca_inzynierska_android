package com.example.praca_inzynierska.forum.data

data class Comment(
    val id: Long,
    val authorId: Long,
    val username: String,
    val content: String,
    val timestamp: String
)