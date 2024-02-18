package com.example.praca_inzynierska.training.requests

import com.example.praca_inzynierska.commons.objects.Global

data class AddTrainingBlockRequest(
    val userId: Long = Global.currentUserId,
    val trainingId: Long,
    val date: String
) {
    constructor(trainingId: Long, date: String) : this(Global.currentUserId, trainingId, date)
}