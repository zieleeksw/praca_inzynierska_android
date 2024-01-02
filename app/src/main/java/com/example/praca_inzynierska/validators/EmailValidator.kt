package com.example.praca_inzynierska.validators

import android.util.Patterns

class EmailValidator(
    private val usernames: List<String>,
    private val email: String
) : Validator() {


    override fun validate(): ValidationResult {
        setResultIfEmailIsTaken()
        setResultIfEmailIsBlank()
        setResultIfEmailIsNotValid()
        return result
    }

    private fun setResultIfEmailIsTaken() {
        if (usernames.contains(email)) {
            updateResultIfNotError("The email is taken")
        }
    }

    private fun setResultIfEmailIsBlank() {
        if (email.isBlank()) {
            updateResultIfNotError("The email can't be Blank")
        }
    }

    private fun setResultIfEmailIsNotValid() {
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            updateResultIfNotError("That's not a valid email")
        }
    }
}