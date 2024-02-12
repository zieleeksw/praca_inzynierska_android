package com.example.praca_inzynierska.nutrition.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.Global.currentUserId
import com.example.praca_inzynierska.Global.token
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.nutrition.data.Food
import com.example.praca_inzynierska.nutrition.services.foodService
import com.example.praca_inzynierska.service.userService
import com.example.praca_inzynierska.states.UserConfigState
import kotlinx.coroutines.launch
import java.time.LocalDate

class FoodScreenViewModel : ViewModel() {

    private val _foodState = mutableStateOf(ResourceState<Food>())
    val foodState: State<ResourceState<Food>> = _foodState

    private val _userConfigState = mutableStateOf(UserConfigState())
    val userConfigState: State<UserConfigState> = _userConfigState

    private val _date = mutableStateOf(LocalDate.now())
    val date: State<LocalDate> = _date

    fun deleteFood(foodId: Long) {
        viewModelScope.launch {
            try {
                foodService.deleteFood("Bearer $token", foodId)
                fetchFoodByDate()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun fetchNecessaryData() {
        fetchFoodByDate()
        fetchUserConfiguration()
    }

    fun fetchFoodByDate() {
        viewModelScope.launch {
            try {
                val foodResponse =
                    foodService.fetchFoodByDate(
                        "Bearer $token",
                        currentUserId,
                        _date.value.toString()
                    )
                _foodState.value = _foodState.value.copy(
                    list = foodResponse,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _foodState.value = _foodState.value.copy(
                    loading = false,
                    error = "Error fetching food data ${e.message}"
                )
            }
        }
    }

    private fun fetchUserConfiguration() {
        viewModelScope.launch {
            try {
                val userConfigResponse =
                    userService.fetchNutritionConfiguration(currentUserId, "Bearer $token")
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = null,
                    userNutritionConfig = userConfigResponse
                )
            } catch (e: Exception) {
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = "Error fetching user configuration data ${e.message}"
                )
            }
        }
    }

    fun onDateChanged(newDate: LocalDate) {
        _date.value = newDate
    }

    fun filterFoodByMeal(meal: String): List<Food> {
        return foodState.value.list.filter { it.meal == meal }
    }

    fun calculateKcal(meal: String): Int {
        if (meal == "") {
            return calculateSum { it.kcal }
        }
        return calculateSumByField(meal) { it.kcal }
    }

    fun calculateFat(meal: String): Int {
        if (meal == "") {
            return calculateSum { it.fat }
        }
        return calculateSumByField(meal) { it.fat }
    }

    fun calculateCarbs(meal: String): Int {
        if (meal == "") {
            return calculateSum { it.carbs }
        }
        return calculateSumByField(meal) { it.carbs }
    }

    fun calculateProtein(meal: String): Int {
        if (meal == "") {
            return calculateSum { it.proteins }
        }
        return calculateSumByField(meal) { it.proteins }
    }

    private fun calculateSum(selector: (Food) -> Int): Int {
        return foodState.value.list
            .sumOf(selector)
    }

    private fun calculateSumByField(meal: String, selector: (Food) -> Int): Int {
        return foodState.value.list
            .filter { it.meal == meal }
            .sumOf(selector)
    }
}