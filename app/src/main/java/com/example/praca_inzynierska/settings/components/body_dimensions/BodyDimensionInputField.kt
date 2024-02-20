package com.example.praca_inzynierska.settings.components.body_dimensions

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.ErrorTextComponent

@Composable
fun BodyDimensionInputField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    error: String?
) {
    Column(modifier = Modifier.padding(horizontal = 64.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = { newValue ->
                if (newValue.length <= 3 && newValue.all { it.isDigit() }) {
                    onValueChange(newValue)
                }
            },
            isError = error != null,
            label = { Text(label) },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            singleLine = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 8.dp),
            textStyle = TextStyle(textAlign = TextAlign.Center),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White,
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                cursorColor = Color.Black,
                unfocusedBorderColor = colorResource(id = R.color.primary_color),
                focusedBorderColor = colorResource(id = R.color.secondary_color)
            )
        )
        ErrorTextComponent(error = error, start = 8, end = 0)
    }
}
