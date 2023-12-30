package com.example.praca_inzynierska.validators

import android.util.Patterns

class EmailValidator(
    private val usernames: List<String>,
    private val email: String
) : Validator() {


    override fun validate(): ValidationResult {
        setResultIfEmailIsBusy()
        setResultIfEmailIsBlank()
        setResultIfEmailIsNotValid()
        return result
    }

    private fun setResultIfEmailIsBusy() {
        if (usernames.contains(email)) {
            updateResultIfNotError("The email is busy")
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