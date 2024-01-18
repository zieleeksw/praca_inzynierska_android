package com.example.praca_inzynierska.view.models.post

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.api_service.postService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeletePostViewModel : ViewModel() {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onClick(postId: Long) {
        viewModelScope.launch {
            try {
                postService.deletePost("Bearer ${Global.token}", postId)
                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
                e.printStackTrace()
            }
        }
    }
}