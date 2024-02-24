package com.example.praca_inzynierska.settings.vm

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praca_inzynierska.commons.objects.Global
import com.example.praca_inzynierska.commons.states.ResourceState
import com.example.praca_inzynierska.nutrition.data.Food
import com.example.praca_inzynierska.settings.enums.SelectedNutrientType
import com.example.praca_inzynierska.settings.services.foodChartService
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class FoodChartViewModel : ViewModel() {

    private val _userFoodState = mutableStateOf(ResourceState<Food>())
    val userFoodState: State<ResourceState<Food>> = _userFoodState

    private val _date = mutableStateOf(LocalDate.now())
    val date: State<LocalDate> = _date

    private val _selectedNutrientType = mutableStateOf(SelectedNutrientType.Kcal)
    val selectedNutrientType: State<SelectedNutrientType> = _selectedNutrientType

    fun onNutrientTypeChanged(newType: SelectedNutrientType) {
        _selectedNutrientType.value = newType
    }

    fun onDateChanged(newDate: LocalDate) {
        _date.value = newDate
    }

    fun getDayFromDate(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val parsedDate = LocalDate.parse(date, formatter)
        return parsedDate.dayOfMonth.toString()
    }

    fun getSortedFood(): List<Food> {
        return _userFoodState.value.list.sortedBy { food ->
            val dayOfMonth = food.date.substringAfterLast("-").toInt()
            dayOfMonth
        }
    }

    fun fetchFoodByYearMonth() {
        viewModelScope.launch {
            try {
                val response = foodChartService.fetchFoodByYearMonth(
                    "Bearer ${Global.token}", Global.currentUserId, formatDateToYearMonth()
                )
                _userFoodState.value = _userFoodState.value.copy(
                    list = response, loading = false, error = null
                )
            } catch (e: Exception) {
                _userFoodState.value = _userFoodState.value.copy(
                    loading = false, error = "Cannot load exercises"
                )
            }
        }
    }

    private fun formatDateToYearMonth(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM")
        return _date.value.format(formatter)
    }

    private fun getMaxValue(propertySelector: (Food) -> Double): String {
        val maxValue = _userFoodState.value.list?.maxOfOrNull(propertySelector) ?: 0.0
        return String.format("%.1f", maxValue)
    }

    private fun getAverageValue(propertySelector: (Food) -> Double): String {
        val foods = _userFoodState.value.list
        if (foods.isEmpty()) return "0.0"

        val averageValue = foods.sumOf(propertySelector) / foods.size
        return String.format("%.1f", averageValue)
    }

    fun getCorrectMaxValue(): Float {
        val selectedType = _selectedNutrientType.value
        val foods = _userFoodState.value.list
        return when (selectedType) {
            SelectedNutrientType.Kcal -> foods.maxOfOrNull { it.kcal.toFloat() } ?: 0f
            SelectedNutrientType.Protein -> foods.maxOfOrNull { it.proteins.toFloat() } ?: 0f
            SelectedNutrientType.Carbs -> foods.maxOfOrNull { it.carbs.toFloat() } ?: 0f
            SelectedNutrientType.Fat -> foods.maxOfOrNull { it.fat.toFloat() } ?: 0f
        }
    }

    fun getSelectedNutrientValues(): List<Float> {
        val foods = _userFoodState.value.list
        return when (_selectedNutrientType.value) {
            SelectedNutrientType.Kcal -> foods.map { it.kcal.toFloat() }
            SelectedNutrientType.Protein -> foods.map { it.proteins.toFloat() }
            SelectedNutrientType.Carbs -> foods.map { it.carbs.toFloat() }
            SelectedNutrientType.Fat -> foods.map { it.fat.toFloat() }
        }
    }

    fun maxKcal() = getMaxValue { it.kcal.toDouble() }
    fun averageKcal() = getAverageValue { it.kcal.toDouble() }
    fun maxProtein() = getMaxValue { it.proteins.toDouble() }
    fun averageProtein() = getAverageValue { it.proteins.toDouble() }
    fun maxCarbs() = getMaxValue { it.carbs.toDouble() }
    fun averageCarbs() = getAverageValue { it.carbs.toDouble() }
    fun maxFat() = getMaxValue { it.fat.toDouble() }
    fun averageFat() = getAverageValue { it.fat.toDouble() }
}

