package com.example.praca_inzynierska.settings

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun LineChart(data: List<Float>, modifier: Modifier = Modifier) {
    // Assume that data is not empty and contains at least one point
    val maxValue = data.maxOrNull() ?: 1f // Avoid division by zero
    val minValue = data.minOrNull() ?: 0f
    val dataRange = maxValue - minValue
    val path = Path()

    Canvas(modifier = modifier) {
        val widthStep = size.width / (data.size - 1).coerceAtLeast(1)
        data.indices.forEach { index ->
            val x = index * widthStep
            val y = (1 - (data[index] - minValue) / dataRange) * size.height
            if (index == 0) {
                path.moveTo(x, y)
            } else {
                path.lineTo(x, y)
            }
        }

        drawPath(
            path = path,
            color = Color.Blue,
            //  strokeWidth = 4.dp.toPx()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewLineChart() {
    MaterialTheme {
        LineChart(
            data = listOf(5f, 10f, 5f, 20f, 15f, 10f, 25f, 10f, 5f),
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1.5f)
        )
    }
}