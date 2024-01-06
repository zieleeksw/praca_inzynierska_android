package com.example.praca_inzynierska.validators.login.register

import com.example.praca_inzynierska.validators.Validator

class ConfirmPasswordValidator(
    private val password: String,
    private val confirmPassword: String
) : Validator() {

    override fun validate(): ValidationResult {
        setResultIfPasswordsNotTheSame()
        return result
    }

    private fun setResultIfPasswordsNotTheSame() {
        if (password != confirmPassword) {
            updateResultIfNotError("The passwords don't match")
        }
    }
}