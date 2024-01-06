package com.example.praca_inzynierska.validators.diet.configuration

import com.example.praca_inzynierska.validators.Validator
import com.example.praca_inzynierska.validators.login.register.ValidationResult
import java.time.DateTimeException
import java.time.LocalDate
import java.time.format.DateTimeParseException

class DateOfBirthValidator(
    private val dayOfBirth: String,
    private val monthOfBirth: String,
    private val yearOfBirth: String
) : Validator() {
    override fun validate(): ValidationResult {
        isAnyFieldBlank()
        validateNumericField()
        isDateValid()
        isUserAtLeast16YearsOld()
        return result
    }

    private fun isAnyFieldBlank() {
        if (dayOfBirth.isBlank() || monthOfBirth.isBlank() || yearOfBirth.isBlank()) {
            updateResultIfNotError("All fields are required")
        }
    }

    private fun validateNumericField() {
        if (areAnyFieldsNonNumeric()) {
            updateResultIfNotError("Enter numeric values to fields")
        }
    }

    private fun areAnyFieldsNonNumeric(): Boolean {
        return dayOfBirth.toIntOrNull() == null ||
                monthOfBirth.toIntOrNull() == null ||
                yearOfBirth.toIntOrNull() == null
    }

    private fun isDateValid() {
        if (!result.successful) {
            return
        }
        try {
            LocalDate.of(yearOfBirth.toInt(), monthOfBirth.toInt(), dayOfBirth.toInt())
        } catch (e: DateTimeException) {
            updateResultIfNotError("Invalid date")
        }
    }

    private fun isUserAtLeast16YearsOld() {
        if (!result.successful) {
            return
        }
        try {
            val birthDate =
                LocalDate.of(yearOfBirth.toInt(), monthOfBirth.toInt(), dayOfBirth.toInt())
            val currentDate = LocalDate.now()
            if (birthDate.plusYears(16).isAfter(currentDate)) {
                updateResultIfNotError("You must be at least 16 years old")
            }
        } catch (e: DateTimeParseException) {
            // Ignored, data correction is checked method before
        }
    }
}