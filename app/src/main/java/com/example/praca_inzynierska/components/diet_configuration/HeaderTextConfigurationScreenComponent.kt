package com.example.praca_inzynierska.components.diet_configuration

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun HeaderTextConfigurationScreenComponent(
    text: String
) {
    Text(
        text = text,
        style = MaterialTheme.typography.headlineLarge.copy(fontSize = 24.sp),
        color = Color.Black,
        textAlign = TextAlign.Center,
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
    )
}