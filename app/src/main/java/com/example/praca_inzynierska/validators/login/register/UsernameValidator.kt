package com.example.praca_inzynierska.validators.login.register

import com.example.praca_inzynierska.validators.Validator

class UsernameValidator(
    private val usernames: List<String>,
    private val username: String
) : Validator() {

    override fun validate(): ValidationResult {
        setResultIfUsernameIsTaken()
        setResultIfUsernameIsTooShort()
        setResultIfUsernameContainsInvalidCharacters()
        return result
    }

    private fun setResultIfUsernameIsTaken() {
        if (usernames.contains(username)) {
            updateResultIfNotError("The username is taken")
        }
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