package com.example.praca_inzynierska.view.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.data.Comment
import com.example.praca_inzynierska.requests.CommentRequest
import com.example.praca_inzynierska.service.commentService
import com.example.praca_inzynierska.states.ResourceState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class CommentsScreenViewModel(
    private val postId: Long
) : ViewModel() {

    private val _commentState = mutableStateOf(ResourceState<Comment>())
    val commentState: MutableState<ResourceState<Comment>> = _commentState
    var addCommentState by mutableStateOf("")
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()
    private var token: String = Global.token

    init {
        fetchComments()
    }

    fun deleteComment(commentId: Long) {
        viewModelScope.launch {
            try {
                commentService.deleteComment("Bearer ${Global.token}", commentId)
                fetchComments()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun addComment(postId: Long) {
        viewModelScope.launch {
            try {
                checkAndAddComment(postId)
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }

    fun onContentChanged(content: String) {
        addCommentState = content
    }

    private suspend fun checkAndAddComment(postId: Long) {
        if (addCommentState.isBlank()) {
            validationEventChannel.send(ValidationEvent.BadCredentials)
            return
        }
        addNewComment(postId)
    }

    private suspend fun addNewComment(postId: Long) {
        val commentRequest = CommentRequest(postId, addCommentState)
        try {
            commentService.addComment("Bearer ${Global.token}", commentRequest)
            addCommentState = ""
            fetchComments()
            validationEventChannel.send(ValidationEvent.Success)
        } catch (e: Exception) {
            e.printStackTrace()
            validationEventChannel.send(ValidationEvent.Failure)
            return
        }
    }

    private fun fetchComments() {
        viewModelScope.launch {
            try {
                val response = commentService.fetchAllComments("Bearer $token", postId)
                _commentState.value = _commentState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _commentState.value = _commentState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }
}