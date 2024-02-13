package com.example.praca_inzynierska.auth.validators

import com.example.praca_inzynierska.commons.validator.Validator

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