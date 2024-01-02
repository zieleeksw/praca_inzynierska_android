package com.example.praca_inzynierska

sealed class ValidationEvent {
    data object Success : ValidationEvent()
    data object Failure : ValidationEvent()
    data object BadCredentials : ValidationEvent()
}