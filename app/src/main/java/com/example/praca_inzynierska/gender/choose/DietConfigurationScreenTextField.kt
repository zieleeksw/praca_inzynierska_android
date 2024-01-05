package com.example.praca_inzynierska.gender.choose

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    value: String
) {

    var fieldValue by remember { mutableStateOf(value) }

    OutlinedTextField(
        value = fieldValue,
        singleLine = true,
        textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
        onValueChange = { fieldValue = it.take(numOfTakenCharacters) },
        shape = MaterialTheme.shapes.medium,
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        modifier = Modifier
            .width(width)
            .padding(end = 8.dp),
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