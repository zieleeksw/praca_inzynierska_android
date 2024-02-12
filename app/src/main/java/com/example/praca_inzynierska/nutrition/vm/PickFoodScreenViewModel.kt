package com.example.praca_inzynierska.nutrition.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global.token
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.nutrition.data.AppFoodModel
import com.example.praca_inzynierska.nutrition.services.foodService
import kotlinx.coroutines.launch

class PickFoodScreenViewModel : ViewModel() {

    private val _foodState = mutableStateOf(ResourceState<AppFoodModel>())
    val foodState: State<ResourceState<AppFoodModel>> = _foodState

    private val _searchText = mutableStateOf("")
    val searchText: State<String> = _searchText

    private val _filteredFood = mutableStateOf<List<AppFoodModel>>(listOf())
    val filteredFood: State<List<AppFoodModel>> = _filteredFood

    fun fetchAllBaseAppFood() {
        viewModelScope.launch {
            _foodState.value = ResourceState(loading = true)
            try {
                val response = foodService.fetchAllBaseAppFood("Bearer $token")
                _foodState.value = ResourceState(loading = false, list = response)
                filteredFood(_searchText.value)
            } catch (e: Exception) {
                _foodState.value = _foodState.value.copy(
                    loading = false,
                    error = "Error fetching food ${e.message}"
                )
            }
        }
    }

    fun onSearchTextChanged(newSearchText: String) {
        _searchText.value = newSearchText
        filteredFood(newSearchText)
    }

    private fun filteredFood(filter: String) {
        _filteredFood.value = _foodState.value.list.filter {
            it.productName.contains(filter, ignoreCase = true)
        }
    }
}
