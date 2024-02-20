package com.example.praca_inzynierska.settings.requests

import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.settings.states.BodyDimensionsState
import java.time.LocalDate

data class DimensionsRequest(
    val userId: Long = Global.currentUserId,
    val date: String = LocalDate.now().toString(),
    val arm: String,
    val chest: String,
    val waist: String,
    val leg: String,
    val calf: String,
) {
    constructor(
        dimensionsState: BodyDimensionsState
    ) : this(
        Global.currentUserId,
        LocalDate.now().toString(),
        dimensionsState.arm,
        dimensionsState.chest,
        dimensionsState.waist,
        dimensionsState.leg,
        dimensionsState.calf,
    )
}
