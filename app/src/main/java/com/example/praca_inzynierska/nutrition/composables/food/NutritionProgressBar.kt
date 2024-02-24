package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R

@Composable
fun NutritionProgressBar(
    title: String,
    currentValue: Int,
    targetValue: Int,
    modifier: Modifier
) {

    val progress = currentValue.toFloat() / targetValue.toFloat()
    val progressBarColor = getProgressBarColor(progress)

    Column(modifier = modifier) {
        Text(text = "$title: $currentValue / $targetValue")
        Spacer(modifier = Modifier.height(2.dp))
        LinearProgressIndicator(
            progress = progress,
            color = progressBarColor
        )
    }
}

@Composable
private fun getProgressBarColor(progress: Float): Color {
    return if (progress > 1f) {
        Color.Red
    } else {
        colorResource(id = R.color.secondary_color)
    }
}