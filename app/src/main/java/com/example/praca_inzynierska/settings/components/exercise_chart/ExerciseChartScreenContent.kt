package com.example.praca_inzynierska.settings.components.exercise_chart

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.settings.components.body_dimensions.BodyDimensionsLegend
import com.example.praca_inzynierska.settings.components.commons.MonthDateSelector
import com.example.praca_inzynierska.settings.vm.ExerciseChartViewModel
import java.time.LocalDate

@Composable
fun ExerciseChartScreenContent(
    viewModel: ExerciseChartViewModel,
    navController: NavController
) {
    MonthDateSelector(
        viewModel.date.value,
        { viewModel.onDateChanged(LocalDate.parse(it)) },
        { viewModel.fetchExercisesByYearMonthAndName() }
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
            ExerciseChart(viewModel)
        }
        Spacer(modifier = Modifier.height(16.dp))
        BodyDimensionsLegend()
        Spacer(modifier = Modifier.height(16.dp))
        LockedFieldsComponents(
            leftLabelTitle = "Max weight",
            leftLabelValue = viewModel.getMaxWeight(),
            rightLabelTitle = "Average weight",
            rightLabelValue = viewModel.getAverageWeight()
        )
        Spacer(modifier = Modifier.height(16.dp))
        LockedFieldsComponents(
            leftLabelTitle = "Max reps",
            leftLabelValue = viewModel.getMaxReps(),
            rightLabelTitle = "Average reps",
            rightLabelValue = viewModel.getAverageReps()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PickExerciseButton(
            onClick = { navController.navigate("${Screens.PickExerciseScreen.name}/CHART") },
            text = viewModel.exerciseState
        )
        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Preview
@Composable
fun ExerciseChartScreenContentPreview() {
    ExerciseChartScreenContent(
        viewModel = ExerciseChartViewModel(),
        navController = rememberNavController()
    )
}