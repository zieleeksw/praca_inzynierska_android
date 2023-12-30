package com.example.praca_inzynierska

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.validators.ConfirmPasswordValidator
import com.example.praca_inzynierska.validators.EmailValidator
import com.example.praca_inzynierska.validators.PasswordValidator
import com.example.praca_inzynierska.validators.UsernameValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {

    var state by mutableStateOf(RegistrationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    private val _usernameState = mutableStateOf(UsernameState())
    val usernameState: State<UsernameState> = _usernameState
    private val _emailState = mutableStateOf(EmailState())
    val emailState: State<EmailState> = _emailState

    init {
        fetchUsernames()
        fetchEmails()
    }

    private fun fetchUsernames() {
        viewModelScope.launch {
            try {
                val response = userService.getUsers()
                _usernameState.value = usernameState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _usernameState.value = _usernameState.value.copy(
                    loading = false,
                    error = "Error fetching usernames ${e.message}"
                )
            }
        }
    }

    private fun fetchEmails() {
        viewModelScope.launch {
            try {
                val response = userService.getEmails()
                _emailState.value = emailState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _emailState.value = _emailState.value.copy(
                    loading = false,
                    error = "Error fetching emails ${e.message}"
                )
            }
        }
    }

    fun onEvent(event: RegistrationFormEvent) {
        when (event) {
            is RegistrationFormEvent.UsernameChanged -> {
                state = state.copy(username = event.username.trim())
            }

            is RegistrationFormEvent.EmailChanged -> {
                state = state.copy(email = event.email.trim())
            }

            is RegistrationFormEvent.PasswordChanged -> {
                state = state.copy(password = event.password.trim())
            }

            is RegistrationFormEvent.ConfirmPasswordChanged -> {
                state = state.copy(confirmPassword = event.confirmPassword.trim())
            }

            is RegistrationFormEvent.Submit -> {
                submitData()
            }
        }
    }

    private fun submitData() {
        val usernameValidator = UsernameValidator(_usernameState.value.list, state.username)
        val usernameResult = usernameValidator.validate()
        val emailValidator = EmailValidator(_emailState.value.list, state.email)
        val emailResult = emailValidator.validate()
        val passwordValidator = PasswordValidator(state.password)
        val passwordResult = passwordValidator.validate()
        val confirmPasswordValidator =
            ConfirmPasswordValidator(state.password, state.confirmPassword)
        val confirmPasswordResult = confirmPasswordValidator.validate()
        val hasError = listOf(
            usernameResult,
            emailResult,
            passwordResult,
            confirmPasswordResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                usernameError = usernameResult.errorMessage,
                emailError = emailResult.errorMessage,
                passwordError = passwordResult.errorMessage,
                confirmPasswordError = confirmPasswordResult.errorMessage,
            )
            return
        }
        viewModelScope.launch {
            registerNewUser()
            state = RegistrationFormState()
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private suspend fun registerNewUser() {
        val user = User(state.username, state.email, state.password)
        try {
            userService.registerUser(user)
        } catch (e: Exception) {
            validationEventChannel.send(ValidationEvent.Failure)
            return
        }
    }

    sealed class ValidationEvent {
        object Success : ValidationEvent()
        object Failure : ValidationEvent()
    }

    data class UsernameState(
        val loading: Boolean = true,
        val list: List<String> = emptyList(),
        val error: String? = null
    )

    data class EmailState(
        val loading: Boolean = true,
        val list: List<String> = emptyList(),
        val error: String? = null
    )
}