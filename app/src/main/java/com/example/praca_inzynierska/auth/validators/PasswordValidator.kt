package com.example.praca_inzynierska.auth.validators

import com.example.praca_inzynierska.commons.validator.Validator

class PasswordValidator(
    private val password: String
) : Validator() {

    override fun validate(): ValidationResult {
        setResultIfPasswordIsTooShort()
        setResultIfPasswordLacksUppercase()
        setResultIfPasswordLacksLowercase()
        setResultIfPasswordLacksDigit()
        setResultIfPasswordLacksSpecialCharacter()
        return result
    }

    private fun setResultIfPasswordIsTooShort() {
        if (password.length < 8) {
            updateResultIfNotError("Requires min. 8 characters.")
        }
    }

    private fun setResultIfPasswordLacksUppercase() {
        if (!password.any { it.isUpperCase() }) {
            updateResultIfNotError("Requires at least one uppercase letter")
        }
    }

    private fun setResultIfPasswordLacksLowercase() {
        if (!password.any { it.isLowerCase() }) {
            updateResultIfNotError("Requires at least one lowercase letter")
        }
    }

    private fun setResultIfPasswordLacksDigit() {
        if (!password.any { it.isDigit() }) {
            updateResultIfNotError("Requires at least one digit")
        }
    }

    private fun setResultIfPasswordLacksSpecialCharacter() {
        val specialCharacters = setOf('!', '@', '#', '$', '%', '^', '&', '*', '.', ',')
        if (!password.any { it in specialCharacters }) {
            updateResultIfNotError("Requires at least one special character")
        }
    }
}