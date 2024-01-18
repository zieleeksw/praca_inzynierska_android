package com.example.praca_inzynierska.components.login_register.textfields

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.ErrorTextComponent
import com.example.praca_inzynierska.components.getVisibilityDescription
import com.example.praca_inzynierska.components.getVisibilityIcon
import com.example.praca_inzynierska.components.getVisualTransformation
import com.example.praca_inzynierska.components.togglePasswordVisibility
import com.example.praca_inzynierska.view.models.RegisterViewModel

@Composable
fun ConfirmPasswordInputWithErrorComponent(
    viewModel: RegisterViewModel
) {
    ConfirmPasswordFieldComponent(viewModel)
    ErrorTextComponent(viewModel.state.confirmPasswordError, 28, 24)
}

@Composable
private fun ConfirmPasswordFieldComponent(
    viewModel: RegisterViewModel
) {

    val passwordVisible = remember { mutableStateOf(false) }
    val state = viewModel.state
    val primaryColor = colorResource(id = R.color.primary_color)
    val secondaryColor = colorResource(id = R.color.secondary_color)

    OutlinedTextField(
        value = state.confirmPassword,
        shape = RoundedCornerShape(16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        isError = state.confirmPasswordError != null,
        leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
        visualTransformation = getVisualTransformation(passwordVisible.value),
        onValueChange = { viewModel.onConfirmPasswordChanged(it) },
        label = { Text(text = stringResource(id = R.string.confirm_password)) },
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = primaryColor,
            focusedBorderColor = secondaryColor,
            focusedLabelColor = secondaryColor,
            cursorColor = secondaryColor
        ),
        trailingIcon = {
            val iconImage = getVisibilityIcon(passwordVisible = passwordVisible.value)
            val description = getVisibilityDescription(passwordVisible = passwordVisible.value)
            IconButton(onClick = { togglePasswordVisibility(passwordVisible) })
            { Icon(imageVector = iconImage, description) }
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp),
    )
}