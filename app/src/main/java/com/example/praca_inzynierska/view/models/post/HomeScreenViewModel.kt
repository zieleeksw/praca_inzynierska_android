package com.example.praca_inzynierska.view.models.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.service.postService
import com.example.praca_inzynierska.states.ResourceState
import kotlinx.coroutines.launch

class HomeScreenViewModel : ViewModel() {

    private val _postState = mutableStateOf(ResourceState<Post>())
    val postState: State<ResourceState<Post>> = _postState
    private var token: String = Global.token

    init {
        fetchPosts()
    }

    fun deletePost(postId: Long) {
        viewModelScope.launch {
            try {
                postService.deletePost("Bearer $token", postId)
                fetchPosts()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val response = postService.fetchAllPosts("Bearer $token")
                _postState.value = _postState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _postState.value = _postState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }
}