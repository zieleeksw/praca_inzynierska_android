package com.example.praca_inzynierska.validators.login.register

import androidx.core.util.PatternsCompat
import com.example.praca_inzynierska.validators.Validator

class EmailValidator(
    private val email: String
) : Validator() {


    override fun validate(): ValidationResult {
        setResultIfEmailIsBlank()
        setResultIfEmailIsNotValid()
        return result
    }

    private fun setResultIfEmailIsBlank() {
        if (email.isBlank()) {
            updateResultIfNotError("The email can't be Blank")
        }
    }

    private fun setResultIfEmailIsNotValid() {
        if (!PatternsCompat.EMAIL_ADDRESS.matcher(email).matches()) {
            updateResultIfNotError("That's not a valid email")
        }
    }
}