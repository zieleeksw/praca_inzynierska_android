package com.example.praca_inzynierska.view.models.food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.api_service.foodApiService
import com.example.praca_inzynierska.states.FoodState
import kotlinx.coroutines.launch

class FetchFoodViewModel(
    var date: String
) : ViewModel() {

    private val _foodState = mutableStateOf(FoodState())
    val foodState: State<FoodState> = _foodState
    private var token: String = Global.token

    init {
        fetchFood()
    }

    private fun fetchFood() {
        viewModelScope.launch {
            try {
                val response =
                    foodApiService.fetchFoodByDate("Bearer $token", Global.currentUserId, date)
                _foodState.value = _foodState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _foodState.value = _foodState.value.copy(
                    loading = false,
                    error = "Error fetching data ${e.message}"
                )
            }
        }
    }
}