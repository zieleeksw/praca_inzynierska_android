package com.example.praca_inzynierska.components.diet_configuration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.components.ErrorTextComponent

@Composable
fun EnterValueOutlinedTextFieldWithError(
    headerText: String,
    textFieldValue: String,
    numOfTakenCharacters: Int,
    value: String,
    isError: Boolean,
    errorString: String?,
    onTextFieldChanged: (String) -> Unit,
) {
    EnterValueOutlinedTextField(
        headerText = headerText,
        numOfTakenCharacters = numOfTakenCharacters,
        textFieldValue = textFieldValue,
        isError = isError,
        value,
        onTextFieldChanged = onTextFieldChanged
    )
    ErrorTextComponent(error = errorString, start = 118, end = 0)
}

@Composable
fun EnterValueOutlinedTextField(
    headerText: String,
    numOfTakenCharacters: Int,
    textFieldValue: String,
    isError: Boolean,
    value: String,
    onTextFieldChanged: (String) -> Unit
) {
    Column(
        modifier = Modifier.padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderTextConfigurationScreenComponent(text = headerText)
        DietConfigurationScreenTextField(
            numOfTakenCharacters = numOfTakenCharacters,
            textFieldValue = textFieldValue,
            width = 124.dp,
            value = value,
            onTextFieldChanged = onTextFieldChanged,
            isError = isError,
        )
    }
}