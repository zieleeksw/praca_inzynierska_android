package com.example.praca_inzynierska

data class RegistrationFormState(
    val email: String = "",
    val emailError: String? = null,
    val password: String = "",
    val passwordError: String? = null,
    val username: String = "",
    val usernameError: String? = null,
    val confirmPassword: String = "",
    val confirmPasswordError: String? = null,
)
