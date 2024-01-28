package com.example.praca_inzynierska.view.models.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.requests.FoodRequest
import com.example.praca_inzynierska.service.foodApiService
import com.example.praca_inzynierska.states.AddFoodState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class AddFoodViewModel : ViewModel() {

    var state by mutableStateOf(AddFoodState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onSubmit() {
        viewModelScope.launch {
            try {
                submit()
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
            }
        }
    }

    private suspend fun submit() {
        if (state.grams.isBlank()) {
            validationEventChannel.send(ValidationEvent.BadCredentials)
            return
        }
        addNewFood()
    }

    private suspend fun addNewFood() {
        val foodRequest = FoodRequest(state)
        if (state.grams.toInt() == 0) {
            validationEventChannel.send(ValidationEvent.BadCredentials)
            return
        }
        try {
            foodApiService.addFood("Bearer ${Global.token}", foodRequest)
            validationEventChannel.send(ValidationEvent.Success)
        } catch (e: Exception) {
            e.printStackTrace()
            validationEventChannel.send(ValidationEvent.Failure)
        }
    }

    fun initConstStateValues(
        date: String,
        meal: String,
        name: String,
    ) {
        state = state.copy(
            date = date,
            meal = meal,
            productName = name.replaceFirstChar { it.uppercaseChar() },
        )
    }

    fun onGramsChanged(
        grams: String?,
        calories: Int,
        fat: Int,
        carbs: Int,
        proteins: Int
    ) {
        val gramsInt = grams?.toIntOrNull() ?: 0
        state = state.copy(
            grams = gramsInt.toString(),
            kcal = ((calories * gramsInt) / 100).toString(),
            fat = ((fat * gramsInt) / 100).toString(),
            carbs = ((carbs * gramsInt) / 100).toString(),
            proteins = ((proteins * gramsInt) / 100).toString()
        )
    }
}