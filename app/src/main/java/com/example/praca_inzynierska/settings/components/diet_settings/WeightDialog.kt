package com.example.praca_inzynierska.settings.components.diet_settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.praca_inzynierska.R

@Composable
fun WeightDialog(
    initialWeight: String,
    onDismiss: () -> Unit,
    onOk: (Double) -> Unit
) {

    var weightText by remember { mutableStateOf(initialWeight) }

    Dialog(onDismissRequest = onDismiss) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.White
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.secondary_color))
                        .padding(12.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        text = "Change weight",
                        color = Color.White
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    IconButton(
                        onClick = {
                            val currentWeight = weightText.toDoubleOrNull() ?: 0.0
                            if (currentWeight > 0) weightText = (currentWeight - 1).toString()
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
                            if (newValue.toDoubleOrNull() != null || newValue.isBlank()) weightText =
                                newValue
                        },
                        isError = weightText.toDoubleOrNull() == null,
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
                            val currentWeight = weightText.toDoubleOrNull() ?: 0.0
                            weightText = (currentWeight + 1.0).toString()
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

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismiss) {
                        Text(text = "Dismiss", color = colorResource(id = R.color.secondary_color))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        onOk(weightText.toDouble())
                    }) {
                        Text("OK", color = colorResource(id = R.color.secondary_color))
                    }
                }
            }
        }
    }
}

private fun updateWeight(
    weightText: String,
    onWeightChange: (String) -> Unit,
    increase: Boolean
) {
    val currentWeight = weightText.toDoubleOrNull() ?: 0.0
    var newWeight = currentWeight + if (increase) 1 else -1
    if (newWeight < 0) newWeight = 0.0

    onWeightChange(newWeight.toString())
}