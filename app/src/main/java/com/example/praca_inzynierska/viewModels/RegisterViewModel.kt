package com.example.praca_inzynierska.viewModels

import androidx.compose.runtime.State
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

    fun onSubmit() {
        val usernameResult = UsernameValidator(_usernameState.value.list, state.username).validate()
        val emailResult = EmailValidator(_emailState.value.list, state.email).validate()
        val passwordResult = PasswordValidator(state.password).validate()
        val confirmPasswordResult = ConfirmPasswordValidator(state.password, state.confirmPassword)
            .validate()

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