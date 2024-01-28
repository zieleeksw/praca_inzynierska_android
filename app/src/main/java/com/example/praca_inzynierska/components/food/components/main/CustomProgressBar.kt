package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R

@Composable
fun CustomProgressBar(
    textBefore: String,
    progress: Float,
    currentValue: String,
    targetValue: String
) {

    val progressBarColor = getProgressBarColor(progress)

    Column(modifier = Modifier.widthIn(150.dp, 175.dp)) {
        Text(text = "${textBefore}: $currentValue / $targetValue")
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

@Preview(showBackground = true)
@Composable
fun PreviewCustomProgressBar() {
    CustomProgressBar("Protein ", progress = 0.6f, "130", "200")
}