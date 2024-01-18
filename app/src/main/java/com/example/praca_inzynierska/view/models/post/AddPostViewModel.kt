package com.example.praca_inzynierska.view.models.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.api_service.postService
import com.example.praca_inzynierska.requests.PostRequest
import com.example.praca_inzynierska.states.AddPostState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddPostViewModel : ViewModel() {

    var state by mutableStateOf(AddPostState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onSubmit() {
        viewModelScope.launch {
            try {
                submit()
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }

    private suspend fun submit() {
        if (state.content.isBlank()) {
            validationEventChannel.send(ValidationEvent.BadCredentials)
            return
        }
        addNewPost()
    }

    private suspend fun addNewPost() {
        val postRequest = PostRequest(state.content)
        try {
            postService.addPost("Bearer ${Global.token}", postRequest)
            validationEventChannel.send(ValidationEvent.Success)
        } catch (e: Exception) {
            e.printStackTrace()
            validationEventChannel.send(ValidationEvent.Failure)
            return
        }
    }

    fun onContentChanged(content: String) {
        state = state.copy(content = content)
    }
}