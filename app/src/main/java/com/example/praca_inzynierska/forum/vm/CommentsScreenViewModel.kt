package com.example.praca_inzynierska.forum.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.Global.token
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.forum.data.Comment
import com.example.praca_inzynierska.forum.states.CreateCommentState
import com.example.praca_inzynierska.requests.CommentRequest
import com.example.praca_inzynierska.service.commentService
import kotlinx.coroutines.launch

class CommentsScreenViewModel : ViewModel() {

    private val _commentState = mutableStateOf(ResourceState<Comment>())
    val commentState: State<ResourceState<Comment>> = _commentState
    var createCommentState by mutableStateOf(CreateCommentState())

    fun deleteComment(
        commentId: Long,
        postId: Long
    ) {
        viewModelScope.launch {
            try {
                commentService.deleteComment("Bearer $token", commentId)
                fetchComments(postId)
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
                e.printStackTrace()
            }
        }
    }

    fun onContentChanged(content: String) {
        createCommentState = createCommentState.copy(content = content)
    }

    private suspend fun checkAndAddComment(postId: Long) {
        if (createCommentState.content.isBlank()) {
            createCommentState = createCommentState.copy(contentError = "You need text")
        } else {
            createCommentState = createCommentState.copy(contentError = null)
            addNewComment(postId)
        }
    }

    private suspend fun addNewComment(postId: Long) {
        val commentRequest = CommentRequest(postId, createCommentState.content)
        try {
            val response = commentService.addComment("Bearer $token", commentRequest)
            if (response.isSuccessful) {
                createCommentState = createCommentState.copy(content = "")
                fetchComments(postId)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun fetchComments(postId: Long) {
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

    fun getAuthorText(comment: Comment): String {
        return if (comment.authorId == Global.currentUserId) {
            "${comment.username} (You)"
        } else {
            comment.username
        }
    }
}