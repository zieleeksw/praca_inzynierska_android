package com.example.praca_inzynierska.diet_configuration.states

import com.example.praca_inzynierska.diet_configuration.enums.ActivityLevel
import com.example.praca_inzynierska.diet_configuration.enums.Gender

data class DietConfigurationFormState(
    val gender: Gender = Gender.FEMALE,
    val dayOfBirth: String = "",
    val monthOfBirth: String = "",
    val yearOfBirth: String = "",
    val dateOfBirthError: String? = null,
    val activityLevel: ActivityLevel = ActivityLevel.VERY_LOW,
    val height: String = "",
    val heightError: String? = null,
    val currentWeight: String = "",
    val currentWeightError: String? = null,
    val targetWeight: String = "",
    val targetWeightError: String? = null,
)
