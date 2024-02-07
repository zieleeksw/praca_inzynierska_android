package com.example.praca_inzynierska.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorTextComponent(
    error: String?,
    start: Int,
    end: Int
) {
    if (error == null) {
        return
    }
    Text(
        text = error,
        color = Color.Red,
        fontSize = 12.sp,
        modifier = Modifier
            .padding(start = start.dp, end = end.dp),
    )
}