package com.example.praca_inzynierska.forum.requests

import com.example.praca_inzynierska.commons.objects.Global

data class PostRequest(
    val authorId: Long = Global.currentUserId,
    val content: String
) {
    constructor(content: String) : this(Global.currentUserId, content)
}
