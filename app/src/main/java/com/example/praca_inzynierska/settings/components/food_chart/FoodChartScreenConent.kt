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
            leftLabelTitle = "Max kcal",
            leftLabelValue = viewModel.maxKcal(),
            rightLabelTitle = "Average kcal",
            rightLabelValue = viewModel.averageKcal()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LockedFieldsComponents(
            leftLabelTitle = "Max protein",
            leftLabelValue = viewModel.maxProtein(),
            rightLabelTitle = "Average protein",
            rightLabelValue = viewModel.averageProtein()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LockedFieldsComponents(
            leftLabelTitle = "Max carbs",
            leftLabelValue = viewModel.maxCarbs(),
            rightLabelTitle = "Average carbs",
            rightLabelValue = viewModel.averageCarbs()
        )
        Spacer(modifier = Modifier.height(8.dp))
        LockedFieldsComponents(
            leftLabelTitle = "Max fat",
            leftLabelValue = viewModel.maxFat(),
            rightLabelTitle = "Average fat",
            rightLabelValue = viewModel.averageFat()
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