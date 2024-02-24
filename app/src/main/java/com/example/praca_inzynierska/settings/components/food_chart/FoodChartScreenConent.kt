package com.example.praca_inzynierska.settings.components.food_chart

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.settings.components.commons.MonthDateSelector
import com.example.praca_inzynierska.settings.components.exercise_chart.LockedFieldsComponents
import com.example.praca_inzynierska.settings.enums.SelectedNutrientType
import com.example.praca_inzynierska.settings.vm.FoodChartViewModel
import java.time.LocalDate

@Composable
fun FoodChartScreenContent(
    viewModel: FoodChartViewModel
) {
    MonthDateSelector(
        viewModel.date.value,
        { viewModel.onDateChanged(LocalDate.parse(it)) },
        { viewModel.fetchFoodByYearMonth() }
    )

    val maxLabelTitle: String
    val maxLabelValue: String
    val avgLabelTitle: String
    val avgLabelValue: String

    when (viewModel.selectedNutrientType.value) {
        SelectedNutrientType.Kcal -> {
            maxLabelTitle = "Max kcal"
            maxLabelValue = viewModel.maxKcal()
            avgLabelTitle = "Average kcal"
            avgLabelValue = viewModel.averageKcal()
        }

        SelectedNutrientType.Protein -> {
            maxLabelTitle = "Max protein"
            maxLabelValue = viewModel.maxProtein()
            avgLabelTitle = "Average protein"
            avgLabelValue = viewModel.averageProtein()
        }

        SelectedNutrientType.Carbs -> {
            maxLabelTitle = "Max carbs"
            maxLabelValue = viewModel.maxCarbs()
            avgLabelTitle = "Average carbs"
            avgLabelValue = viewModel.averageCarbs()
        }

        SelectedNutrientType.Fat -> {
            maxLabelTitle = "Max fat"
            maxLabelValue = viewModel.maxFat()
            avgLabelTitle = "Average fat"
            avgLabelValue = viewModel.averageFat()
        }
    }

    Column {
        Card(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .weight(1f),
            elevation = CardDefaults.cardElevation(defaultElevation = Ui.DEFAULT_CARD_ELEVATION),
            shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            FoodChart(viewModel)
        }
        Spacer(modifier = Modifier.height(8.dp))
        DropDownMenuFood(viewModel)
        Spacer(modifier = Modifier.height(8.dp))
        LockedFieldsComponents(
            leftLabelTitle = maxLabelTitle,
            leftLabelValue = maxLabelValue,
            rightLabelTitle = avgLabelTitle,
            rightLabelValue = avgLabelValue
        )
        Spacer(modifier = Modifier.height(8.dp))
        LockedFieldsComponents(
            leftLabelTitle = "Days counted",
            leftLabelValue = viewModel.userFoodState.value.list.size.toString(),
            rightLabelTitle = "Current month day",
            rightLabelValue = viewModel.getDayFromDate(viewModel.date.value.toString())
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}