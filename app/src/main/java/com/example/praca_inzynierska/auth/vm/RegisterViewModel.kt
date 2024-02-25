package com.example.praca_inzynierska.auth.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.auth.requests.UserRegisterRequest
import com.example.praca_inzynierska.auth.services.userService
import com.example.praca_inzynierska.auth.states.RegistrationFormState
import com.example.praca_inzynierska.auth.validators.ConfirmPasswordValidator
import com.example.praca_inzynierska.auth.validators.EmailValidator
import com.example.praca_inzynierska.auth.validators.PasswordValidator
import com.example.praca_inzynierska.auth.validators.UsernameValidator
import com.example.praca_inzynierska.auth.validators.ValidationResult
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())

    fun onSubmit(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                validate(onSuccess)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun validate(afterSuccess: () -> Unit) {
        val usernameResponse = userService.isUsernameAvailable(state.username)
        val emailResponse = userService.isEmailAvailable(state.email)
        if (usernameResponse.isSuccessful && emailResponse.isSuccessful) {
            val usernameResult = validateUsername(usernameResponse.body())
            val emailResult = validateEmail(emailResponse.body())
            val passwordResult = PasswordValidator(state.password).validate()
            val confirmPasswordResult =
                ConfirmPasswordValidator(state.password, state.confirmPassword).validate()
            if (hasError(
                    usernameResult,
                    emailResult,
                    passwordResult,
                    confirmPasswordResult
                )
            ) {
                updateStateErrors(
                    usernameResult,
                    emailResult,
                    passwordResult,
                    confirmPasswordResult
                )
            } else {
                onSuccess(afterSuccess)
            }
        }
    }

    private fun validateUsername(usernameResponseResult: Boolean?): ValidationResult {
        return if (usernameResponseResult != null && !usernameResponseResult) {
            ValidationResult(successful = false, "The username is taken")
        } else {
            UsernameValidator(state.username).validate()
        }
    }

    private fun validateEmail(emailResponseResult: Boolean?): ValidationResult {
        return if (emailResponseResult != null && !emailResponseResult) {
            ValidationResult(successful = false, "The email is taken")
        } else {
            EmailValidator(state.email).validate()
        }
    }

    private fun updateStateErrors(
        usernameResult: ValidationResult,
        emailResult: ValidationResult,
        passwordResult: ValidationResult,
        confirmPasswordResult: ValidationResult
    ) {
        state = state.copy(
            usernameError = if (!usernameResult.successful) usernameResult.errorMessage else null,
            emailError = if (!emailResult.successful) emailResult.errorMessage else null,
            passwordError = if (!passwordResult.successful) passwordResult.errorMessage else null,
            confirmPasswordError = if (!confirmPasswordResult.successful) confirmPasswordResult.errorMessage else null
        )
    }

    private fun hasError(
        usernameResult: ValidationResult,
        emailResult: ValidationResult,
        passwordResult: ValidationResult,
        confirmPasswordResult: ValidationResult
    ): Boolean {
        return listOf(
            usernameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }
    }

    private suspend fun onSuccess(afterSuccess: () -> Unit) {
        registerNewUser(afterSuccess)
        state = RegistrationFormState()
    }


    private suspend fun registerNewUser(afterSuccess: () -> Unit) {
        val user = UserRegisterRequest(state.username, state.email, state.password)
        try {
            val response = userService.registerUser(user)
            if (response.isSuccessful) {
                afterSuccess()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun onEmailChanged(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        state = state.copy(password = password)
    }

    fun onConfirmPasswordChanged(confirmPassword: String) {
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onUsernameChanged(username: String) {
        state = state.copy(username = username)
    }
}