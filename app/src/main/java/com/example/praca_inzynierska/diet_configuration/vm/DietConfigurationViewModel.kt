package com.example.praca_inzynierska.diet_configuration.vm

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.diet_configuration.enums.ActivityLevel
import com.example.praca_inzynierska.diet_configuration.enums.Gender
import com.example.praca_inzynierska.diet_configuration.requests.UserNutritionConfigRequest
import com.example.praca_inzynierska.diet_configuration.services.userNutritionService
import com.example.praca_inzynierska.diet_configuration.states.DietConfigurationFormState
import com.example.praca_inzynierska.diet_configuration.validators.DateOfBirthValidator
import com.example.praca_inzynierska.diet_configuration.validators.HeightValidator
import com.example.praca_inzynierska.diet_configuration.validators.WeightValidator
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.time.LocalDate

class DietConfigurationViewModel : ViewModel() {

    var state by mutableStateOf(DietConfigurationFormState())
    private val validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = validationEventChannel.receiveAsFlow()

    fun onSubmit() {
        val dateResult =
            DateOfBirthValidator(state.dayOfBirth, state.monthOfBirth, state.yearOfBirth).validate()
        val heightResult = HeightValidator(state.height).validate()
        val currentWeightResult = WeightValidator(state.currentWeight).validate()
        val targetWeightResult = WeightValidator(state.targetWeight).validate()

        val hasError = listOf(
            dateResult,
            heightResult,
            currentWeightResult,
            targetWeightResult
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                dateOfBirthError = dateResult.errorMessage,
                heightError = heightResult.errorMessage,
                currentWeightError = currentWeightResult.errorMessage,
                targetWeightError = targetWeightResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            addNutritionConfig()
            validationEventChannel.send(ValidationEvent.Success)
        }
    }

    private suspend fun addNutritionConfig() {

        val birthDate = LocalDate.of(
            state.yearOfBirth.toInt(),
            state.monthOfBirth.toInt(),
            state.dayOfBirth.toInt()
        )

        val userNutritionConfig = UserNutritionConfigRequest(
            state.gender.text,
            birthDate.toString(),
            state.activityLevel.text,
            state.height.toInt(),
            state.currentWeight.toDouble(),
            state.targetWeight.toDouble()
        )

        try {
            userNutritionService.addNutritionConfiguration(
                Global.currentUserId,
                "Bearer ${Global.token}",
                userNutritionConfig
            )
        } catch (e: Exception) {
            validationEventChannel.send(ValidationEvent.Failure)
            return
        }
    }

    fun onGenderChanged(gender: Gender) {
        state = state.copy(gender = gender)
    }

    fun onDayOfBirthChanged(dayOfBirth: String) {
        state = state.copy(dayOfBirth = dayOfBirth)
    }

    fun onMonthOfBirthChanged(monthOfBirth: String) {
        state = state.copy(monthOfBirth = monthOfBirth)
    }

    fun onYearOfBirthChanged(yearOfBirth: String) {
        state = state.copy(yearOfBirth = yearOfBirth)
    }

    fun onActivityLevelChanged(activityLevel: ActivityLevel) {
        state = state.copy(activityLevel = activityLevel)
    }

    fun onHeightChanged(height: String) {
        state = state.copy(height = height)
    }

    fun onCurrentWeightChanged(currentWeight: String) {
        if (!currentWeight.contains(",")) {
            state = state.copy(currentWeight = currentWeight)
        }
    }

    fun onTargetWeightChanged(targetWeight: String) {
        if (!targetWeight.contains(",")) {
            state = state.copy(targetWeight = targetWeight)
        }
    }
}