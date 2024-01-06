package com.example.praca_inzynierska.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.states.LoginFormState
import com.example.praca_inzynierska.LoginRequest
import com.example.praca_inzynierska.User
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.userService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onSubmit() {
        viewModelScope.launch {
            try {
                val loginRequest = LoginRequest(state.email, state.password)
                val response = userService.login(loginRequest)

                if (response.isSuccessful) {
                    val user: User? = response.body()
                    validationEventChannel.send(ValidationEvent.Success)
                    println(user)
                } else {
                    state = state.copy(loginError = true)
                    validationEventChannel.send(ValidationEvent.BadCredentials)
                }
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }

    fun onEmailChanged(email: String) {
        state = state.copy(email = email)
    }

    fun onPasswordChanged(password: String) {
        state = state.copy(password = password)
    }
}

