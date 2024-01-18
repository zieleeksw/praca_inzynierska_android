package com.example.praca_inzynierska.view.models.food

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.api_service.foodApiService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class DeleteFoodViewModel : ViewModel() {


    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onClick(foodId: Long) {
        viewModelScope.launch {
            try {
                foodApiService.deleteFood("Bearer ${Global.token}", foodId)
                validationEventChannel.send(ValidationEvent.Success)
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
                e.printStackTrace()
            }
        }
    }
}