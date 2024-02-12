package com.example.praca_inzynierska.nutrition.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global.token
import com.example.praca_inzynierska.commons.states.SingleResourceState
import com.example.praca_inzynierska.nutrition.data.AppFoodModel
import com.example.praca_inzynierska.nutrition.requests.FoodRequest
import com.example.praca_inzynierska.nutrition.services.foodService
import com.example.praca_inzynierska.nutrition.states.AddFoodState
import kotlinx.coroutines.launch
import java.util.Locale

class HandleFoodScreenViewModel : ViewModel() {

    private val _foodByNameState = mutableStateOf(SingleResourceState<AppFoodModel>())
    val foodByNameState: State<SingleResourceState<AppFoodModel>> = _foodByNameState

    var addState by mutableStateOf(AddFoodState())

    fun fetchFoodByName(name: String) {

        _foodByNameState.value = _foodByNameState.value.copy(loading = true, error = null)

        viewModelScope.launch {
            try {
                val response = foodService.fetchBaseAppFoodByName("Bearer $token", name)
                if (response.isSuccessful && response.body() != null) {
                    _foodByNameState.value = _foodByNameState.value.copy(
                        resource = response.body()!!,
                        loading = false
                    )
                    onGramsChanged(addState.grams)
                }
            } catch (e: Exception) {
                _foodByNameState.value = _foodByNameState.value.copy(
                    loading = false,
                    error = e.message ?: "An error occurred."
                )
            }
        }
    }

    fun onSubmit(
        date: String,
        meal: String,
    ) {
        viewModelScope.launch {
            try {
                submit(date, meal)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun submit(
        date: String,
        meal: String,
    ) {
        if (addState.grams.isBlank() || addState.grams == "0") {
            _foodByNameState.value = _foodByNameState.value.copy(error = "Enter correct value")
            return
        }
        addNewFood(date, meal)
    }

    private suspend fun addNewFood(date: String, meal: String) {

        val appFoodModel = _foodByNameState.value.resource ?: return

        val productName = appFoodModel.productName.replaceFirstChar {
            if (it.isLowerCase()) it.titlecase(
                Locale.getDefault()
            ) else it.toString()
        }

        val foodRequest = FoodRequest(
            addState,
            date,
            meal,
            productName
        )

        if (addState.grams.toInt() == 0) return

        try {
            val response = foodService.addFood("Bearer $token", foodRequest)
            if (response.isSuccessful) {
                addState = addState.copy(error = null, isSuccessful = true)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onGramsChanged(
        grams: String
    ) {

        val appFoodModel = _foodByNameState.value.resource

        if (grams.isBlank()) {
            addState = addState.copy(
                grams = "",
                kcal = "",
                fat = "",
                carbs = "",
                proteins = ""
            )
        } else {
            val gramsInt = grams.toIntOrNull() ?: return
            addState = addState.copy(
                grams = gramsInt.toString(),
                kcal = calculateNutrientValue(appFoodModel?.kcal, gramsInt),
                fat = calculateNutrientValue(appFoodModel?.fat, gramsInt),
                carbs = calculateNutrientValue(appFoodModel?.carbs, gramsInt),
                proteins = calculateNutrientValue(appFoodModel?.proteins, gramsInt)
            )
        }
    }

    private fun calculateNutrientValue(nutrientValue: Int?, grams: Int): String {
        return if (nutrientValue != null) {
            ((nutrientValue * grams) / 100).toString()
        } else {
            "0"
        }
    }
}
