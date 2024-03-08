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
            updateResultIfNotError("Requires min. 6 characters.")
        }
    }

    private fun setResultIfUsernameContainsInvalidCharacters() {
        if (username.any { !it.isLetterOrDigit() }) {
            updateResultIfNotError("Only letters and numbers allowed.")
        }
    }
}