package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.settings.data.BodyDimensions
import com.example.praca_inzynierska.settings.requests.DimensionsRequest
import com.example.praca_inzynierska.settings.services.dimensionsService
import com.example.praca_inzynierska.settings.states.BodyDimensionsState
import kotlinx.coroutines.launch

class BodyDimensionsViewModel : ViewModel() {

    private val _bodyDimensions = mutableStateOf(ResourceState<BodyDimensions>())
    val bodyDimensions: State<ResourceState<BodyDimensions>> = _bodyDimensions

    var bodyDimensionsState by mutableStateOf(BodyDimensionsState())

    fun addBodyDimensions(onSuccess: () -> Unit) {
        viewModelScope.launch {
            try {
                checkAndAddBodyDimensions(onSuccess)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun checkAndAddBodyDimensions(onSuccess: () -> Unit) {
        var errorOccurred = false

        val isArmInvalid =
            bodyDimensionsState.arm.isBlank() || !bodyDimensionsState.arm.all { it.isDigit() }
        val isChestInvalid =
            bodyDimensionsState.chest.isBlank() || !bodyDimensionsState.chest.all { it.isDigit() }
        val isWaistInvalid =
            bodyDimensionsState.waist.isBlank() || !bodyDimensionsState.waist.all { it.isDigit() }
        val isLegInvalid =
            bodyDimensionsState.leg.isBlank() || !bodyDimensionsState.leg.all { it.isDigit() }
        val isCalfInvalid =
            bodyDimensionsState.calf.isBlank() || !bodyDimensionsState.calf.all { it.isDigit() }

        bodyDimensionsState = bodyDimensionsState.copy(
            armError = if (isArmInvalid) "Must be an integer number" else null,
            chestError = if (isChestInvalid) "Must be an integer number" else null,
            waistError = if (isWaistInvalid) "Must be an integer number" else null,
            legError = if (isLegInvalid) "Must be an integer number" else null,
            calfError = if (isCalfInvalid) "Must be an integer number" else null
        )

        if (isArmInvalid || isChestInvalid || isWaistInvalid || isLegInvalid || isCalfInvalid) {
            errorOccurred = true
        }

        if (!errorOccurred) {
            addUserBodyDimensions(onSuccess)
        }
    }

    private suspend fun addUserBodyDimensions(onSuccess: () -> Unit) {
        val dimensionsRequest = DimensionsRequest(bodyDimensionsState)
        try {
            val response =
                dimensionsService.addDimensions("Bearer ${Global.token}", dimensionsRequest)
            if (response.isSuccessful) {
                bodyDimensionsState = BodyDimensionsState()
                onSuccess()
                fetchDimensions()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return
        }
    }

    fun fetchDimensions() {
        viewModelScope.launch {
            try {
                val response =
                    dimensionsService.fetchDimensions(
                        "Bearer ${Global.token}",
                        Global.currentUserId
                    )
                _bodyDimensions.value = _bodyDimensions.value.copy(
                    list = response,
                    loading = false,
                    error = null
                )
            } catch (e: Exception) {
                _bodyDimensions.value = _bodyDimensions.value.copy(
                    loading = false,
                    error = "Cannot load dimensions"
                )
            }
        }
    }

    fun deleteDimensions(dimensionsId: Long) {
        viewModelScope.launch {
            try {
                dimensionsService.deleteDimensions(
                    "Bearer ${Global.token}",
                    dimensionsId
                )
                fetchDimensions()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun onArmChanged(value: String) {
        bodyDimensionsState = bodyDimensionsState.copy(arm = value)
    }

    fun onChestChanged(value: String) {
        bodyDimensionsState = bodyDimensionsState.copy(chest = value)
    }

    fun onWaistChanged(value: String) {
        bodyDimensionsState = bodyDimensionsState.copy(waist = value)
    }

    fun onLegChanged(value: String) {
        bodyDimensionsState = bodyDimensionsState.copy(leg = value)
    }

    fun onCalfChanged(value: String) {
        bodyDimensionsState = bodyDimensionsState.copy(calf = value)
    }
}