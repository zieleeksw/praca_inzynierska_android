package com.example.praca_inzynierska.view.models.comments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.api_service.commentService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeleteCommentViewModel : ViewModel() {

    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onClick(commentId: Long) {
        viewModelScope.launch {
            try {
                commentService.deleteComment("Bearer ${Global.token}", commentId)
                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: Exception) {
                e.printStackTrace()
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }
}