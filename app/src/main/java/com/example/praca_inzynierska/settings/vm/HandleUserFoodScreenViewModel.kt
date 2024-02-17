package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.nutrition.data.AppFoodModel
import com.example.praca_inzynierska.settings.requests.UserFoodRequest
import com.example.praca_inzynierska.settings.services.userFoodService
import com.example.praca_inzynierska.settings.states.UserFoodState
import kotlinx.coroutines.launch

class HandleUserFoodScreenViewModel : ViewModel() {

    private val _userFoodState = mutableStateOf(ResourceState<AppFoodModel>())
    val userFoodState: State<ResourceState<AppFoodModel>> = _userFoodState

    var foodState by mutableStateOf(UserFoodState())

    fun onNameChanged(name: String) {
        foodState = foodState.copy(name = name)
    }

    fun onKcalChanged(kcal: String) {
        foodState = foodState.copy(kcal = kcal)
    }

    fun onFatChanged(fat: String) {
        foodState = foodState.copy(fat = fat)
    }

    fun onCarbsChanged(carbs: String) {
        foodState = foodState.copy(carbs = carbs)
    }

    fun onProteinChanged(protein: String) {
        foodState = foodState.copy(protein = protein)
    }

    fun addFood(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                checkAndAddFood(onSuccess)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun checkAndAddFood(onSuccess: () -> Unit) {
        var errorOccurred = false

        val isNameBlank = foodState.name.isBlank()
        val isKcalInvalid = foodState.kcal.isBlank() || !foodState.kcal.all { it.isDigit() }
        val isFatInvalid = foodState.fat.isBlank() || !foodState.fat.all { it.isDigit() }
        val isCarbsInvalid = foodState.carbs.isBlank() || !foodState.carbs.all { it.isDigit() }
        val isProteinInvalid =
            foodState.protein.isBlank() || !foodState.protein.all { it.isDigit() }

        if (isNameBlank) {
            foodState = foodState.copy(onError = "You need to add food name")
            errorOccurred = true
        } else {
            foodState = foodState.copy(onError = null)
        }

        foodState = foodState.copy(
            onKcalError = if (isKcalInvalid) "Kcal must be an integer number" else null,
            onFatError = if (isFatInvalid) "Fat must be an integer number" else null,
            onCarbsError = if (isCarbsInvalid) "Carbs must be an integer number" else null,
            onProteinError = if (isProteinInvalid) "Protein must be an integer number" else null
        )

        if (isKcalInvalid || isFatInvalid || isCarbsInvalid || isProteinInvalid) {
            errorOccurred = true
        }

        if (!errorOccurred) {
            addUserFood(onSuccess)
        }
    }

    private suspend fun addUserFood(onSuccess: () -> Unit) {
        val userFoodRequest = UserFoodRequest(foodState)
        try {
            val response =
                userFoodService.addUserFood("Bearer ${Global.token}", userFoodRequest)
            if (response.isSuccessful) {
                if (response.body()!!) {
                    foodState = UserFoodState()
                    onSuccess()
                    fetchUserFood()
                } else {
                    foodState =
                        foodState.copy(onError = "Exercise with that name already exists")
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun fetchUserFood() {
        viewModelScope.launch {
            try {
                val response =
                    userFoodService.fetchUserFood(
                        "Bearer ${Global.token}",
                        Global.currentUserId
                    )
                _userFoodState.value = _userFoodState.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _userFoodState.value = _userFoodState.value.copy(
                    loading = false,
                    error = "Cannot load exercises"
                )
            }
        }
    }

    fun deleteUserFoodByName(
        foodName: String
    ) {
        viewModelScope.launch {
            try {
                userFoodService.deleteUserFoodByName(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    foodName
                )
                fetchUserFood()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onDismiss() {
        foodState = UserFoodState()
    }
}