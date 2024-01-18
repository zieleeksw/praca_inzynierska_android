package com.example.praca_inzynierska.requests

import com.example.praca_inzynierska.Global

data class PostRequest(
    val authorId: Long = Global.currentUserId,
    val content: String
) {
    constructor(content: String) : this(Global.currentUserId, content)
}
