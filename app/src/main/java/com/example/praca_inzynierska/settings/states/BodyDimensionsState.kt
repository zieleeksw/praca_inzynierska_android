package com.example.praca_inzynierska.settings.states

data class BodyDimensionsState(
    val arm: String = "",
    val armError: String? = null,
    val chest: String = "",
    val chestError: String? = null,
    val waist: String = "",
    val waistError: String? = null,
    val leg: String = "",
    val legError: String? = null,
    val calf: String = "",
    val calfError: String? = null
)
