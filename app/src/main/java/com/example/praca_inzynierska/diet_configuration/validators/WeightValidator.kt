package com.example.praca_inzynierska.diet_configuration.validators

import com.example.praca_inzynierska.auth.validators.ValidationResult
import com.example.praca_inzynierska.commons.validator.Validator

class WeightValidator(
    private val weight: String
) : Validator() {
    override fun validate(): ValidationResult {
        isWeightFieldBlank()
        isWeightFormatValid()
        return result
    }

    private fun isWeightFieldBlank() {
        if (weight.isBlank()) {
            updateResultIfNotError("You have to enter weight")
        }
    }

    private fun isWeightFormatValid() {
        if (!(isTwoDigitWholeNumber() || isTwoDigitDecimalNumber() || isThreeDigitNumber() || isThreeDigitDecimalNumber())) {
            updateResultIfNotError("Invalid weight format")
        }
    }

    private fun isTwoDigitWholeNumber(): Boolean {
        return weight.matches(Regex("\\d{2}"))
    }

    private fun isTwoDigitDecimalNumber(): Boolean {
        return weight.matches(Regex("\\d{2}[.]\\d*"))
    }

    private fun isThreeDigitNumber(): Boolean {
        return weight.matches(Regex("\\d{3}"))
    }

    private fun isThreeDigitDecimalNumber(): Boolean {
        return weight.matches(Regex("\\d{3}[.]\\d"))
    }
}