package com.example.praca_inzynierska.view.models.comments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.api_service.commentService
import com.example.praca_inzynierska.requests.CommentRequest
import com.example.praca_inzynierska.states.AddCommentState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddCommentViewModel : ViewModel() {

    var state by mutableStateOf(AddCommentState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onSubmit(postId: Long) {
        viewModelScope.launch {
            try {
                submit(postId)
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }

    private suspend fun submit(postId: Long) {
        if (state.content.isBlank()) {
            validationEventChannel.send(ValidationEvent.BadCredentials)
            return
        }
        addNewComment(postId)
    }

    private suspend fun addNewComment(postId: Long) {
        val commentRequest = CommentRequest(postId, state.content)
        try {
            commentService.addComment("Bearer ${Global.token}", commentRequest)
            state = state.copy(content = "", contentError = false)
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