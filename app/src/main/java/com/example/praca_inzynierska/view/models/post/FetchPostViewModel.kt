package com.example.praca_inzynierska.view.models.post

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.api_service.postService
import com.example.praca_inzynierska.states.PostState
import kotlinx.coroutines.launch

class FetchPostViewModel : ViewModel() {

    private val _postState = mutableStateOf(PostState())
    val postState: State<PostState> = _postState
    private var token: String = Global.token

    init {
        fetchPosts()
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