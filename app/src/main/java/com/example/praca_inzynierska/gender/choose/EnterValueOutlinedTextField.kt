package com.example.praca_inzynierska.gender.choose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EnterValueOutlinedTextField(
    headerText: String,
    textFieldValue: String,
    numOfTakenCharacters: Int
) {

    val currentValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderTextConfigurationScreenComponent(text = headerText)
        DietConfigurationScreenTextField(
            numOfTakenCharacters = numOfTakenCharacters,
            textFieldValue = textFieldValue,
            width = 124.dp,
            value = currentValue
        )
    }
}