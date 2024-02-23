package com.example.praca_inzynierska.settings.components.exercise_chart

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui

@Composable
fun StatsTextField(
    value: String,
    label: String
) {
    TextField(
        value = value,
        onValueChange = {},
        singleLine = true,
        enabled = false,
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .widthIn(min = 64.dp),
        textStyle = LocalTextStyle.current.copy(
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        ),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            disabledContainerColor = Color.White,
            errorContainerColor = Color.White,
            disabledIndicatorColor = colorResource(id = R.color.secondary_color),
            unfocusedIndicatorColor = colorResource(id = R.color.secondary_color),
            focusedIndicatorColor = colorResource(id = R.color.secondary_color),
            unfocusedTextColor = Color.Gray,
            focusedTextColor = Color.Gray,
            disabledTextColor = Color.Gray,
            cursorColor = Color.Black
        )
    )
    Text(text = label, textAlign = TextAlign.Center)
}