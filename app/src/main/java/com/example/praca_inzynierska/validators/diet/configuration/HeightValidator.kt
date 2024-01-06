package com.example.praca_inzynierska.validators.diet.configuration

import com.example.praca_inzynierska.validators.Validator
import com.example.praca_inzynierska.validators.login.register.ValidationResult

class HeightValidator(
    private val height: String
) : Validator() {
    override fun validate(): ValidationResult {
        setResultIfHeightIsBlank()
        setResultIfHeightIsNotNumeric()
        setResultIfHeightIsNotThreeDigitsLong()
        return result
    }

    private fun setResultIfHeightIsBlank() {
        if (height.isBlank()) {
            updateResultIfNotError("You have to enter height")
        }
    }

    private fun setResultIfHeightIsNotNumeric() {
        if (height.toIntOrNull() == null) {
            updateResultIfNotError("Height must contain only numeric digits")
        }
    }

    private fun setResultIfHeightIsNotThreeDigitsLong() {
        if (height.length != 3) {
            updateResultIfNotError("Height must be three digits long")
        }
    }
}
