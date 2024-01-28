package com.example.praca_inzynierska.view.models.food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.service.foodApiService
import com.example.praca_inzynierska.states.FoodState
import kotlinx.coroutines.launch

class FoodScreenViewModel(
    var date: String
) : ViewModel() {

    private val _foodState = mutableStateOf(FoodState())
    val foodState: State<FoodState> = _foodState
    private var token: String = Global.token

    init {
        fetchFood()
    }

    fun deleteFood(foodId: Long) {
        viewModelScope.launch {
            try {
                foodApiService.deleteFood("Bearer ${Global.token}", foodId)
                fetchFood()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
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