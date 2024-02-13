package com.example.praca_inzynierska.forum.requests

import com.example.praca_inzynierska.commons.objects.Global

data class CommentRequest(
    val authorId: Long = Global.currentUserId,
    val postId: Long,
    val content: String
) {
    constructor(postId: Long, content: String) : this(Global.currentUserId, postId, content)
}
