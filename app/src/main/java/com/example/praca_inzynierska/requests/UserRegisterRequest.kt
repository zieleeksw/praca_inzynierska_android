package com.example.praca_inzynierska.requests

data class UserRegisterRequest(
    private val username: String,
    private val email: String,
    private val password: String
)