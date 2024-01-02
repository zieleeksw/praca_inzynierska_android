package com.example.praca_inzynierska

data class LoginFormState(
    val email: String = "",
    val loginError: Boolean = false,
    val password: String = "",
)
