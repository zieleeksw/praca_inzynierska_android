package com.example.praca_inzynierska.view.models.comments

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.api_service.commentService
import com.example.praca_inzynierska.states.CommentState
import kotlinx.coroutines.launch

class FetchCommentViewModel(
    private val postId: Long
) : ViewModel() {

    private val _commentState = mutableStateOf(CommentState())
    val commentState: MutableState<CommentState> = _commentState
    private var token: String = Global.token

    init {
        fetchComments()
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