package com.example.praca_inzynierska.auth.validators

data class ValidationResult(
    val successful: Boolean,
    val errorMessage: String? = null
)
