package com.example.praca_inzynierska.view.models.food

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.requests.FoodRequest
import com.example.praca_inzynierska.service.foodApiService
import com.example.praca_inzynierska.states.AddFoodState
import kotlinx.coroutines.launch

class AddFoodViewModel : ViewModel() {

    var state by mutableStateOf(AddFoodState())

    fun onSubmit() {
        viewModelScope.launch {
            try {
                submit()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun submit() {
        if (state.grams.isBlank() || state.grams == "0") {
            state = state.copy(error = "Enter correct value")
            return
        }
        addNewFood()
    }

    private suspend fun addNewFood() {
        val foodRequest = FoodRequest(state)
        if (state.grams.toInt() == 0) {
            return
        }
        try {
            val response = foodApiService.addFood("Bearer ${Global.token}", foodRequest)
            if (response.isSuccessful) {
                state = state.copy(error = null, isSuccessful = true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
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
        grams: String,
        calories: String,
        fat: String,
        carbs: String,
        proteins: String
    ) {
        if (grams.isBlank()) {
            state = state.copy(
                grams = "",
                kcal = "",
                fat = "",
                carbs = "",
                proteins = ""
            )
        } else {
            val gramsInt = grams.toInt()
            state = state.copy(
                grams = gramsInt.toString(),
                kcal = (((calories.toIntOrNull() ?: 0) * gramsInt) / 100).toString(),
                fat = (((fat.toIntOrNull() ?: 0) * gramsInt) / 100).toString(),
                carbs = (((carbs.toIntOrNull() ?: 0) * gramsInt) / 100).toString(),
                proteins = (((proteins.toIntOrNull() ?: 0) * gramsInt) / 100).toString()
            )
        }
    }
}