package com.example.praca_inzynierska.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.UserRegisterRequest
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.states.RegistrationFormState
import com.example.praca_inzynierska.userService
import com.example.praca_inzynierska.validators.login.register.ConfirmPasswordValidator
import com.example.praca_inzynierska.validators.login.register.EmailValidator
import com.example.praca_inzynierska.validators.login.register.PasswordValidator
import com.example.praca_inzynierska.validators.login.register.UsernameValidator
import com.example.praca_inzynierska.validators.login.register.ValidationResult
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onSubmit() {
        viewModelScope.launch {
            try {
                validate()
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }

    private suspend fun validate() {
        val usernameResponse = userService.isUsernameAvailable(state.username)
        val emailResponse = userService.isEmailAvailable()
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
                onSuccess()
            }
        } else {
            validationEventChannel.send(ValidationEvent.Failure)
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

    private suspend fun onSuccess() {
        registerNewUser()
        state = RegistrationFormState()
        validationEventChannel.send(ValidationEvent.Success)
    }


    private suspend fun registerNewUser() {
        val user = UserRegisterRequest(state.username, state.email, state.password)
        try {
            userService.registerUser(user)
        } catch (e: Exception) {
            validationEventChannel.send(ValidationEvent.Failure)
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