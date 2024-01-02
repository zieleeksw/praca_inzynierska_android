package com.example.praca_inzynierska

data class UserRegisterRequest(
    private val username: String,
    private val email: String,
    private val password: String
)

data class User(
    private val id: Long,
    private val username: String,
    private val email: String,
    private val token: String
)
