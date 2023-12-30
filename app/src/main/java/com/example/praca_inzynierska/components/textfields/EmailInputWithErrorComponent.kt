package com.example.praca_inzynierska.components.textfields

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
import com.example.praca_inzynierska.RegisterViewModel
import com.example.praca_inzynierska.RegistrationFormEvent

@Composable
fun EmailInputWithErrorComponent(
    viewModel: RegisterViewModel
) {
    EmailFieldComponent(viewModel)
    ErrorTextComponent(viewModel.state.emailError)
}

@Composable
private fun EmailFieldComponent(viewModel: RegisterViewModel) {

    val state = viewModel.state
    val primaryColor = colorResource(id = R.color.primary_color)
    val secondaryColor = colorResource(id = R.color.secondary_color)

    OutlinedTextField(
        value = state.email,
        isError = state.emailError != null,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions.Default,
        label = { Text(text = stringResource(id = R.string.email)) },
        onValueChange = { viewModel.onEvent(RegistrationFormEvent.EmailChanged(it)) },
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
