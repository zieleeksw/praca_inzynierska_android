package com.example.praca_inzynierska.view.models.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global.token
import com.example.praca_inzynierska.service.foodApiService
import com.example.praca_inzynierska.states.FoodByNameState
import kotlinx.coroutines.launch

class FetchFoodViewModel : ViewModel() {

    var state by mutableStateOf(FoodByNameState())
        private set

    fun fetchFood(name: String) {

        state = state.copy(isLoading = true, error = null)

        viewModelScope.launch {
            try {
                val response = foodApiService.fetchBaseAppFoodByName("Bearer $token", name)
                state = if (response.isSuccessful && response.body() != null) {
                    state.copy(
                        appFoodModel = response.body()!!,
                        isLoading = false
                    )
                } else {
                    state.copy(
                        isLoading = false,
                        error = "Failed to fetch food details."
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = e.message ?: "An error occurred."
                )
            }
        }
    }
}