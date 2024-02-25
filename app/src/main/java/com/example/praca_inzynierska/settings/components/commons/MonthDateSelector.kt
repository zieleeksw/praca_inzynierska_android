package com.example.praca_inzynierska.settings.components.commons

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
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
import java.util.Locale

@Composable
fun MonthDateSelector(
    currentMonth: LocalDate,
    onMonthChanged: (String) -> Unit,
    afterMonthChange: () -> Unit
) {
    val formatter = DateTimeFormatter.ofPattern("MMMM, yyyy", Locale.ENGLISH)

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .background(color = colorResource(id = R.color.secondary_color))
    ) {
        IconButton(onClick = {
            onMonthChanged(
                currentMonth.minusMonths(1).toString()
            )
            afterMonthChange()
        }) {
            Icon(Icons.Default.ArrowBack, contentDescription = "Previous Month", tint = Color.White)
        }
        Text(
            text = formatter.format(currentMonth),
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
        IconButton(onClick = {
            onMonthChanged(
                currentMonth.plusMonths(1).toString()
            )
            afterMonthChange()
        }) {
            Icon(Icons.Default.ArrowForward, contentDescription = "Next Month", tint = Color.White)
        }
    }
}