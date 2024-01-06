package com.example.praca_inzynierska.validators

import com.example.praca_inzynierska.validators.login.register.ValidationResult

abstract class Validator {

    protected var result = ValidationResult(successful = true)

    abstract fun validate(): ValidationResult

    protected fun updateResultIfNotError(errorMessage: String) {
        if (result.successful) {
            result = ValidationResult(successful = false, errorMessage = errorMessage)
        }
    }
}