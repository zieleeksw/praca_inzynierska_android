package com.example.praca_inzynierska.validators

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
