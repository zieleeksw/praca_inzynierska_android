package com.example.praca_inzynierska.components.general

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import com.example.praca_inzynierska.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun DateSelector(
    currentDate: MutableState<LocalDate>,
    onDateChange: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding()
            .background(color = colorResource(id = R.color.secondary_color))
    ) {
        IconButton(onClick = {
            currentDate.value = currentDate.value.minusDays(1)
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous Day", tint = Color.White)
            onDateChange()
        }
        Text(
            text = currentDate.value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = {
            currentDate.value = currentDate.value.plusDays(1)
            onDateChange()
        }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Next Day", tint = Color.White)
        }
    }
}