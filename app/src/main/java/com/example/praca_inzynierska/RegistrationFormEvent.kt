package com.example.praca_inzynierska

sealed class RegistrationFormEvent {
    data class UsernameChanged(val username: String) : RegistrationFormEvent()
    data class EmailChanged(val email: String) : RegistrationFormEvent()
    data class PasswordChanged(val password: String) : RegistrationFormEvent()
    data class ConfirmPasswordChanged(val confirmPassword: String) : RegistrationFormEvent()

    object Submit: RegistrationFormEvent()
}