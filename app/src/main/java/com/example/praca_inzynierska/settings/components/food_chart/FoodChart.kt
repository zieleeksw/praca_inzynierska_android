package com.example.praca_inzynierska.settings.components.food_chart

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
import com.example.praca_inzynierska.settings.vm.FoodChartViewModel

@Composable
fun FoodChart(
    viewModel: FoodChartViewModel
) {

    val food = viewModel.getSortedFood()
    val maxValue = viewModel.getCorrectMaxValue()
    val secondaryColor = colorResource(id = R.color.secondary_color)
    val yAxisSteps = 4
    val yAxisValues = (0..yAxisSteps).map { ((it.toFloat() / yAxisSteps) * maxValue).toInt() }
    val dataSize = food.size
    val selectedNutrientValues = viewModel.getSelectedNutrientValues()

    Row(modifier = Modifier.padding(4.dp)) {
        Column(
            modifier = Modifier.fillMaxHeight(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            if (food.isEmpty()) {
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
                    if (dataSize > 1) (size.width - totalSpacing) / dataSize else size.width / 2

                selectedNutrientValues.forEachIndexed { index, value ->
                    val barHeight = (value / maxValue) * size.height
                    drawRoundRect(
                        color = secondaryColor,
                        topLeft = Offset(
                            x = index * (barWidth + spaceBetweenGroups),
                            y = size.height - barHeight
                        ),
                        size = Size(width = barWidth, height = barHeight),
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
                food.forEach { food ->
                    Column(
                        modifier = Modifier
                            .weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = viewModel.getDayFromDate(food.date),
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