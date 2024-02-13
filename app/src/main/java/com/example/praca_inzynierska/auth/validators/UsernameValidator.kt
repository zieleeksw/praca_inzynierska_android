package com.example.praca_inzynierska.auth.validators

import com.example.praca_inzynierska.commons.validator.Validator

class UsernameValidator(
    private val username: String
) : Validator() {

    override fun validate(): ValidationResult {
        setResultIfUsernameIsTooShort()
        setResultIfUsernameContainsInvalidCharacters()
        return result
    }

    private fun setResultIfUsernameIsTooShort() {
        if (username.length < 6) {
            updateResultIfNotError("The username must be at least 6 characters long")
        }
    }

    private fun setResultIfUsernameContainsInvalidCharacters() {
        if (username.any { !it.isLetterOrDigit() }) {
            updateResultIfNotError("The username must contain only letters and numbers")
        }
    }
}