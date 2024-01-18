package com.example.praca_inzynierska.requests

import com.example.praca_inzynierska.Global

data class FollowPostRequest(
    val userId: Long = Global.currentUserId,
    val postId: Long
) {
    constructor(postId: Long) : this(Global.currentUserId, postId)
}