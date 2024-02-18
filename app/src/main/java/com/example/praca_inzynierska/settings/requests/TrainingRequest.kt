package com.example.praca_inzynierska.settings.requests

import com.example.praca_inzynierska.commons.objects.Global

data class TrainingRequest(
    val userId: Long = Global.currentUserId,
    val name: String
) {
    constructor(name: String) : this(Global.currentUserId, name)
}