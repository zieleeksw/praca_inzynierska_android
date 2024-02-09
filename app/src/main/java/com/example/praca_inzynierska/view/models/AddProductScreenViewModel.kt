package com.example.praca_inzynierska.view.models

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.data.AppFoodModel
import com.example.praca_inzynierska.service.foodApiService
import com.example.praca_inzynierska.states.ResourceState
import kotlinx.coroutines.launch

class AddProductScreenViewModel : ViewModel() {


    private val _foodState = mutableStateOf(ResourceState<AppFoodModel>())
    val foodState: State<ResourceState<AppFoodModel>> = _foodState

    init {
        fetchAllBaseAppFood()
    }

    private fun fetchAllBaseAppFood() {
        viewModelScope.launch {
            try {
                val response =
                    foodApiService.fetchAllBaseAppFood("Bearer ${Global.token}")
                _foodState.value = _foodState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _foodState.value = _foodState.value.copy(
                    loading = false,
                    error = "Error fetching Posts ${e.message}"
                )
            }
        }
    }
}