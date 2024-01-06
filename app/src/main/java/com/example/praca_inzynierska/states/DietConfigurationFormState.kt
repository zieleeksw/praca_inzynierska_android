package com.example.praca_inzynierska.states

import com.example.praca_inzynierska.enums.Gender

data class DietConfigurationFormState(
    val gender: Gender = Gender.FEMALE,
    val dayOfBirth: String = "",
    val monthOfBirth: String = "",
    val yearOfBirth: String = "",
    val dateOfBirthError: String? = null,
    val height: String = "",
    val heightError: String? = null,
    val currentWeight: String = "",
    val currentWeightError: String? = null,
    val targetWeight: String = "",
    val targetWeightError: String? = null,
)
