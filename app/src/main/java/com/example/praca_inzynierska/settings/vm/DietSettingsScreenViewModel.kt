package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.SingleResourceState
import com.example.praca_inzynierska.diet_configuration.data.UserNutritionConfig
import com.example.praca_inzynierska.diet_configuration.services.userNutritionService
import kotlinx.coroutines.launch

class DietSettingsScreenViewModel : ViewModel() {

    private val _userConfigState = mutableStateOf(SingleResourceState<UserNutritionConfig>())
    val userConfigState: State<SingleResourceState<UserNutritionConfig>> = _userConfigState

    fun fetchUserConfiguration() {
        viewModelScope.launch {
            try {
                val userConfigResponse =
                    userNutritionService.fetchNutritionConfiguration(
                        Global.currentUserId,
                        "Bearer ${Global.token}"
                    )
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = null,
                    resource = userConfigResponse
                )
            } catch (e: Exception) {
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = "Error fetching user configuration data ${e.message}"
                )
            }
        }
    }

    fun changeActivityLevel(activityLevel: String) {
        viewModelScope.launch {
            try {
                val response = userNutritionService.changeActivityLevel(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    activityLevel
                )
                if (response.isSuccessful) {
                    fetchUserConfiguration()
                }
            } catch (e: Exception) {
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = "Error adding data ${e.message}"
                )
            }
        }
    }

    fun updateCurrentWeight(newWeight: Double) {
        viewModelScope.launch {
            try {
                val response = userNutritionService.updateCurrentWeight(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    newWeight
                )
                if (response.isSuccessful) {
                    fetchUserConfiguration()
                }
            } catch (e: Exception) {
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = "Error adding data ${e.message}"
                )
            }
        }
    }

    fun updateTargetWeight(newWeight: Double) {
        viewModelScope.launch {
            try {
                val response = userNutritionService.updateTargetWeight(
                    "Bearer ${Global.token}",
                    Global.currentUserId,
                    newWeight
                )
                if (response.isSuccessful) {
                    fetchUserConfiguration()
                }
            } catch (e: Exception) {
                _userConfigState.value = _userConfigState.value.copy(
                    loading = false,
                    error = "Error adding data ${e.message}"
                )
            }
        }
    }
}