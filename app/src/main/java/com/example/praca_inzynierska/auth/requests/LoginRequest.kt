package com.example.praca_inzynierska.auth.requests

data class LoginRequest(
    val email: String = "",
    val password: String = "",
)