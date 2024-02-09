package com.example.praca_inzynierska.data


data class Post(
    val id: Long,
    val authorId: Long,
    val author: String,
    val content: String,
    val timestamp: String,
    var followers: List<Long>
)
