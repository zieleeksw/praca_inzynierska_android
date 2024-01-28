package com.example.praca_inzynierska.view.models

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.LoginRequest
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.data.User
import com.example.praca_inzynierska.service.userService
import com.example.praca_inzynierska.states.LoginFormState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    var user by mutableStateOf<User?>(null)

    fun onSubmit() {
        viewModelScope.launch {
            try {

                val loginRequest = LoginRequest(state.email, state.password)
                val response = userService.login(loginRequest)

                if (response.isSuccessful) {
                    user = response.body()
                    if (user != null) {
                        Global.currentUserId = user!!.id
                        Global.token = user!!.token
                        validationEventChannel.send(ValidationEvent.Success)
                    }
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

