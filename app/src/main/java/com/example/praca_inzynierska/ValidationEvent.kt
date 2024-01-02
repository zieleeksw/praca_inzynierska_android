package com.example.praca_inzynierska

sealed class ValidationEvent {
    object Success : ValidationEvent()
    object Failure : ValidationEvent()
    object BadCredentials : ValidationEvent()
}