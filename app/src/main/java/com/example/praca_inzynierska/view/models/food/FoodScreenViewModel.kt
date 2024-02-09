package com.example.praca_inzynierska.view.models.food

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.data.Food
import com.example.praca_inzynierska.service.foodApiService
import com.example.praca_inzynierska.service.userService
import com.example.praca_inzynierska.states.ResourceState
import com.example.praca_inzynierska.states.UserConfigState
import kotlinx.coroutines.launch

class FoodScreenViewModel(
    var date: String
) : ViewModel() {

    private val _foodState = mutableStateOf(ResourceState<Food>())
    val foodState: State<ResourceState<Food>> = _foodState
    private val _userConfigState = mutableStateOf(UserConfigState())
    val userConfigState: State<UserConfigState> = _userConfigState
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

    fun fetchFood() {
        viewModelScope.launch {
            try {
                val foodResponse =
                    foodApiService.fetchFoodByDate("Bearer $token", Global.currentUserId, date)
                _foodState.value = _foodState.value.copy(
                    list = foodResponse,
                    loading = false,
                    error = null
                )
                val userConfigResponse =
                    userService.fetchNutritionConfiguration(Global.currentUserId, "Bearer $token")
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = null,
                    userNutritionConfig = userConfigResponse
                )
            } catch (e: Exception) {
                _foodState.value = _foodState.value.copy(
                    loading = false,
                    error = "Error fetching data ${e.message}"
                )
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = "Error fetching data ${e.message}"
                )
            }
        }
    }
}