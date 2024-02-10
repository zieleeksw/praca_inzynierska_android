package com.example.praca_inzynierska.components.login_register.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.ErrorTextComponent

@Composable
fun EmailInputWithErrorComponent(
    email: String,
    errorMessage: String?,
    isError: Boolean,
    onEmailChanged: (String) -> Unit,
) {
    EmailFieldComponent(email, isError, onEmailChanged)
    ErrorTextComponent(errorMessage, 28, 24)
}

@Composable
private fun EmailFieldComponent(
    email: String,
    isError: Boolean,
    onEmailChanged: (String) -> Unit,
) {

    val primaryColor = colorResource(id = R.color.primary_color)
    val secondaryColor = colorResource(id = R.color.secondary_color)

    OutlinedTextField(
        value = email,
        isError = isError,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions.Default,
        label = { Text(text = stringResource(id = R.string.email)) },
        onValueChange = { onEmailChanged(it) },
        leadingIcon = { Icon(Icons.Default.Email, contentDescription = "Email field") },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = primaryColor,
            focusedBorderColor = secondaryColor,
            focusedLabelColor = secondaryColor,
            cursorColor = secondaryColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
    )
}
