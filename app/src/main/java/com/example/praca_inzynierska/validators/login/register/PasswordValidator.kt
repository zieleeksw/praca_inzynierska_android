package com.example.praca_inzynierska.validators.login.register

import com.example.praca_inzynierska.validators.Validator

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
            updateResultIfNotError("The password needs to consist at least 8 characters")
        }
    }

    private fun setResultIfPasswordLacksUppercase() {
        if (!password.any { it.isUpperCase() }) {
            updateResultIfNotError("The password must contain at least one uppercase letter")
        }
    }

    private fun setResultIfPasswordLacksLowercase() {
        if (!password.any { it.isLowerCase() }) {
            updateResultIfNotError("The password must contain at least one lowercase letter")
        }
    }

    private fun setResultIfPasswordLacksDigit() {
        if (!password.any { it.isDigit() }) {
            updateResultIfNotError("The password must contain at least one digit")
        }
    }

    private fun setResultIfPasswordLacksSpecialCharacter() {
        val specialCharacters = setOf('!', '@', '#', '$', '%', '^', '&', '*', '.', ',')
        if (!password.any { it in specialCharacters }) {
            updateResultIfNotError("The password must contain at least one special character")
        }
    }
}