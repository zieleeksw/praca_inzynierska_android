package com.example.praca_inzynierska.requests

import com.example.praca_inzynierska.Global

class CommentRequest(
    val authorId: Long = Global.currentUserId,
    val postId: Long,
    val content: String
) {
    constructor(postId: Long, content: String) : this(Global.currentUserId, postId, content)
}
