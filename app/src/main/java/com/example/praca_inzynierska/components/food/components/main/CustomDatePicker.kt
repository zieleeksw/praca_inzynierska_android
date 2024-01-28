package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.res.colorResource
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.screens.convertMillisToDateString

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomDatePicker(
    onDisableDialog: () -> Unit,
    onPickDate: (String) -> Unit
) {

    val datePickerState = rememberDatePickerState()
    val confirmEnabled by remember(datePickerState.selectedDateMillis) {
        derivedStateOf { datePickerState.selectedDateMillis != null }
    }

    DatePickerDialog(
        onDismissRequest = { onDisableDialog() },
        confirmButton = {
            TextButton(
                onClick = {
                    onDisableDialog()
                    val date: String
                    if (datePickerState.selectedDateMillis != null) {
                        date =
                            convertMillisToDateString(datePickerState.selectedDateMillis!!)
                        onPickDate(date)
                    }
                },
                enabled = confirmEnabled
            ) {
                Text(text = "Okay")
            }
        }) {
        DatePicker(
            state = datePickerState,
            colors = DatePickerDefaults.colors(
                dayContentColor = colorResource(id = R.color.primary_color),
                titleContentColor = colorResource(id = R.color.primary_color),
                headlineContentColor = colorResource(id = R.color.primary_color),
                weekdayContentColor = colorResource(id = R.color.primary_color),
                subheadContentColor = colorResource(id = R.color.primary_color)
            )
        )
    }
}