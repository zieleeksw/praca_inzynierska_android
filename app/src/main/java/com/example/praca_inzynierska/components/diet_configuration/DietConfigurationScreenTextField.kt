package com.example.praca_inzynierska.components.diet_configuration

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R

@Composable
fun DietConfigurationScreenTextField(
    numOfTakenCharacters: Int,
    textFieldValue: String,
    width: Dp,
    value: String,
    isError: Boolean,
    onTextFieldChanged: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        onValueChange = { onTextFieldChanged(it.take(numOfTakenCharacters)) },
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        isError = isError,
        modifier = Modifier
            .width(width)
            .height(58.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = colorResource(id = R.color.primary_color),
            focusedBorderColor = colorResource(id = R.color.secondary_color),
        ),
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = textFieldValue,
                style = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
            )
        },
    )
}