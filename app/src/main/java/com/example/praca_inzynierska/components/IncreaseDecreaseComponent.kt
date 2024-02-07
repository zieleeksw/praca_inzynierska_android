package com.example.praca_inzynierska.components

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
import androidx.compose.material3.Divider
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R

@Composable
fun IncreaseDecreaseComponent(
    title: String,
    onValueChange: Double,
    intReturnValue: Boolean,
    weightText: String,
    onError: String?,
    onWeightTextChange: (String) -> Unit
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
        Divider(
            color = colorResource(id = R.color.secondary_color),
            thickness = 1.dp
        )
        Spacer(Modifier.height(4.dp))
        Row {
            IconButton(
                onClick = {
                    updateWeight(
                        weightText,
                        { onWeightTextChange(it) },
                        onValueChange,
                        intReturnValue,
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
                value = weightText,
                onValueChange = { newValue ->
                    if (!newValue.contains(',')) {
                        onWeightTextChange(newValue)
                    }
                },
                isError = onError != null,
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .widthIn(32.dp),
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
                ),
                textStyle = LocalTextStyle.current.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                )
            )
            IconButton(
                onClick = {
                    updateWeight(
                        weightText,
                        { onWeightTextChange(it) },
                        onValueChange,
                        intReturnValue,
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
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            ErrorTextComponent(onError, 0, 0)
        }
    }
}

private fun updateWeight(
    weightText: String,
    onWeightChange: (String) -> Unit,
    onChangeValue: Double,
    intReturnValue: Boolean,
    increase: Boolean
) {
    val currentWeight = weightText.toDoubleOrNull() ?: 0.0
    var newWeight = currentWeight + if (increase) onChangeValue else -onChangeValue
    if (newWeight < 0) newWeight = 0.0
    val newTextValue = if (intReturnValue) {
        newWeight.toInt().toString()
    } else {
        newWeight.toString()
    }
    onWeightChange(newTextValue)
}

@Preview(showBackground = true)
@Composable
fun PreviewWeightControlItem() {
    IncreaseDecreaseComponent(
        title = "Test Weight Control",
        onValueChange = 1.0,
        intReturnValue = false,
        weightText = "100",
        onError = "null",
        onWeightTextChange = {}
    )
}
