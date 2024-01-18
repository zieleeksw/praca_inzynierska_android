package com.example.praca_inzynierska.components.login_register.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
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
import com.example.praca_inzynierska.components.ErrorTextComponent
import com.example.praca_inzynierska.view.models.RegisterViewModel

@Composable
fun UsernameInputWithErrorComponent(
    viewModel: RegisterViewModel
) {
    UsernameFieldComponent(viewModel)
    ErrorTextComponent(viewModel.state.usernameError, 28, 24)
}


@Composable
fun UsernameFieldComponent(viewModel: RegisterViewModel) {

    val state = viewModel.state
    val primaryColor = colorResource(id = R.color.primary_color)
    val secondaryColor = colorResource(id = R.color.secondary_color)

    OutlinedTextField(
        value = state.username,
        isError = state.usernameError != null,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions.Default,
        label = { Text(text = stringResource(id = R.string.username)) },
        onValueChange = { viewModel.onUsernameChanged(it) },
        leadingIcon = { Icon(Icons.Default.AccountCircle, contentDescription = "Username field") },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = primaryColor,
            focusedBorderColor = secondaryColor,
            focusedLabelColor = secondaryColor,
            cursorColor = secondaryColor
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 8.dp),
    )
}