package com.example.praca_inzynierska.commons.components

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
    currentDate: LocalDate,
    onDateChanged: (String) -> Unit,
    afterDateChange: () -> Unit
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
            onDateChanged(currentDate.minusDays(1).toString())
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous Day", tint = Color.White)
            afterDateChange()
        }
        Text(
            text = currentDate.toString().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = {
            onDateChanged(currentDate.plusDays(1).toString())
            afterDateChange()
        }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Next Day", tint = Color.White)
        }
    }
}