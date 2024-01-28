package com.example.praca_inzynierska.view.models

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.service.foodApiServiceExt
import com.example.praca_inzynierska.states.NutritionState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

class FetchFoodViewModel : ViewModel() {

    private val _nutritionState = mutableStateOf(NutritionState())
    val nutritionState: State<NutritionState> = _nutritionState
    var foodToSearchState by mutableStateOf("")
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun fetchNutrition() {
        viewModelScope.launch {
            try {
                val response = foodApiServiceExt.fetchNutritionData(foodToSearchState)
                if (response.isSuccessful) {
                    _nutritionState.value = _nutritionState.value.copy(
                        list = response.body(),
                        loading = false,
                        error = null
                    )
                    validationEventChannel.send(ValidationEvent.Success)
                }
            } catch (e: Exception) {
                validationEventChannel.send(ValidationEvent.Failure)
                _nutritionState.value = _nutritionState.value.copy(
                    loading = false,
                    error = "Error fetching nutrition ${e.message}"
                )
            }
        }
    }

    fun onFoodToSearchState(toSearch: String) {
        foodToSearchState = toSearch
    }
}