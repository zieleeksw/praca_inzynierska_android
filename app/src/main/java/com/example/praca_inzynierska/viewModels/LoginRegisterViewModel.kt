package com.example.praca_inzynierska.viewModels

interface LoginRegisterViewModel {

    fun onSubmit()

    fun onEmailChanged(email: String)

    fun onPasswordChanged(password: String)
}