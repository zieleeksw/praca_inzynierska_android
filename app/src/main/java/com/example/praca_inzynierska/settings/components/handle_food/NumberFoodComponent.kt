package com.example.praca_inzynierska.settings.components.handle_food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.ErrorTextComponent
import com.example.praca_inzynierska.commons.components.SecondaryColorDivider

@Composable
fun NumberFoodComponent(
    title: String,
    value: String,
    onValueChanged: (String) -> Unit,
    onError: String?
) {

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = title,
            modifier = Modifier.align(Alignment.Start),
            color = Color.Gray
        )
        Spacer(Modifier.height(2.dp))
        SecondaryColorDivider()
        Spacer(Modifier.height(4.dp))
        Row {
            IconButton(
                onClick = {
                    updateWeight(
                        value,
                        { onValueChanged(it) },
                        false
                    )

                },
                modifier = Modifier
                    .background(
                        Color.Transparent,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .padding(4.dp)
            ) {
                Icon(
                    Icons.Filled.Remove,
                    "Decrease",
                    tint = colorResource(id = R.color.secondary_color)
                )
            }
            TextField(
                value = value,
                onValueChange = { newValue ->
                    if (newValue.length <= 3 && newValue.all { it.isDigit() }) {
                        onValueChanged(newValue)
                    }
                },
                isError = onError != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .widthIn(32.dp),
                textStyle = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
                colors = TextFieldDefaults.colors(
                    unfocusedContainerColor = Color.White,
                    focusedContainerColor = Color.White,
                    disabledContainerColor = Color.White,
                    errorContainerColor = Color.White,
                    disabledIndicatorColor = Color.LightGray,
                    unfocusedIndicatorColor = Color.LightGray,
                    focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                    unfocusedTextColor = Color.Gray,
                    focusedTextColor = Color.Gray,
                    disabledTextColor = Color.Gray,
                    cursorColor = Color.Black
                )
            )
            IconButton(
                onClick = {
                    updateWeight(
                        value,
                        { onValueChanged(it) },
                        true
                    )
                },
                modifier = Modifier
                    .background(
                        Color.Transparent, shape = RoundedCornerShape(4.dp)
                    )
                    .padding(4.dp)
            ) {
                Icon(
                    Icons.Filled.Add,
                    "Increase",
                    tint = colorResource(id = R.color.secondary_color)
                )
            }
        }
        ErrorTextComponent(onError, 0, 0)
    }
}

private fun updateWeight(
    weightText: String,
    onWeightChange: (String) -> Unit,
    increase: Boolean
) {
    val currentValue = weightText.toIntOrNull() ?: 0
    val newWeight = currentValue + if (increase) 1 else -1
    onWeightChange(newWeight.toString())
}