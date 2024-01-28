package com.example.praca_inzynierska.view.models.post

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.requests.FollowPostRequest
import com.example.praca_inzynierska.service.postService
import kotlinx.coroutines.launch

class FollowClickViewModel(
    var id: Long,
    isFollowed: Boolean
) : ViewModel() {

    var state by mutableStateOf(isFollowed)

    fun onFollowClick() {
        viewModelScope.launch {
            try {
                val request = FollowPostRequest(id)
                val response = postService.followPost("Bearer ${Global.token}", request)
                if (response.isSuccessful) {
                    state = !state
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}