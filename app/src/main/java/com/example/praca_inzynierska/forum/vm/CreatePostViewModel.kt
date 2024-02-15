package com.example.praca_inzynierska.forum.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.forum.requests.PostRequest
import com.example.praca_inzynierska.forum.services.postService
import com.example.praca_inzynierska.forum.states.CreatePostState
import kotlinx.coroutines.launch

class CreatePostViewModel : ViewModel() {

    var state by mutableStateOf(CreatePostState())

    var isAddedState by mutableStateOf(false)

    fun onContentChanged(content: String) {
        state = state.copy(content = content)
    }

    fun onSubmit() {
        viewModelScope.launch {
            try {
                submit()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun submit() {
        if (state.content.isBlank()) run {
            state = state.copy(contentError = "Content of post is necessary")
            return
        }
        addNewPost()
    }

    private suspend fun addNewPost() {
        val postRequest = PostRequest(state.content)
        try {
            val response = postService.addPost("Bearer ${Global.token}", postRequest)
            if (response.isSuccessful) {
                isAddedState = true
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }
}