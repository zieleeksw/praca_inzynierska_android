package com.example.praca_inzynierska.settings.components.exercise_chart

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.settings.vm.ExerciseChartViewModel


@Composable
fun ExerciseChart(
    viewModel: ExerciseChartViewModel
) {

    val exercises = viewModel.getSortedExercises()
    val maxValue = exercises.maxOfOrNull { it.weight.toFloat() } ?: 0f
    val secondaryColor = colorResource(id = R.color.secondary_color)
    val primaryColor = colorResource(id = R.color.primary_color)
    val yAxisSteps = 4
    val yAxisValues = (0..yAxisSteps).map { ((it.toFloat() / yAxisSteps) * maxValue).toInt() }
    val dataSize = exercises.size

    Row(modifier = Modifier.padding(4.dp)) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            if (exercises.isEmpty()) {
                Text("")
            } else {
                yAxisValues.reversed().forEach { value ->
                    Text(
                        text = value.toString(),
                        modifier = Modifier,
                        color = secondaryColor,
                        fontSize = 12.sp
                    )
                }
            }
        }
        Spacer(modifier = Modifier.width(4.dp))
        Column(modifier = Modifier.fillMaxSize()) {
            Canvas(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                val spaceBetweenGroups =
                    if (dataSize > 1) size.width * 0.1f / (dataSize - 1) else 0f
                val totalSpacing = if (dataSize > 1) spaceBetweenGroups * (dataSize - 1) else 0f
                val barWidth =
                    if (dataSize > 1) (size.width - totalSpacing) / (dataSize * 2) else size.width / 2
                exercises.forEachIndexed { index, exercise ->
                    val barHeight = exercise.weight.toFloat() / maxValue * size.height
                    drawRoundRect(
                        color = secondaryColor,
                        topLeft = Offset(
                            x = index * 2 * barWidth + index * spaceBetweenGroups,
                            y = size.height - barHeight
                        ),
                        size = Size(width = barWidth, height = barHeight),
                        cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx())
                    )
                    val secondBarHeight = exercise.repetition.toFloat() / maxValue * size.height
                    drawRoundRect(
                        color = primaryColor,
                        topLeft = Offset(
                            x = (index * 2 + 1) * barWidth + index * spaceBetweenGroups,
                            y = size.height - secondBarHeight
                        ),
                        size = Size(width = barWidth, height = secondBarHeight),
                        cornerRadius = CornerRadius(x = 4.dp.toPx(), y = 4.dp.toPx())
                    )
                }
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                exercises.forEach { exercise ->
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = viewModel.getDayFromDate(exercise.date),
                            color = secondaryColor,
                            modifier = Modifier.padding(top = 4.dp),
                            textAlign = TextAlign.Center,
                            fontSize = 12.sp
                        )
                    }
                }
            }
        }
    }
}
