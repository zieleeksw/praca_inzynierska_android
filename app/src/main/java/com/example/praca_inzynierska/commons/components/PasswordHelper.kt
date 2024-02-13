package com.example.praca_inzynierska.commons.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.praca_inzynierska.R


@Composable
fun getVisibilityIcon(passwordVisible: Boolean): ImageVector {
    if (passwordVisible) {
        return Icons.Filled.Visibility
    }
    return Icons.Filled.VisibilityOff
}

@Composable
fun getVisibilityDescription(passwordVisible: Boolean): String {
    if (passwordVisible) {
        return stringResource(id = R.string.show_password)
    }
    return stringResource(id = R.string.hide_password)
}

@Composable
fun getVisualTransformation(passwordVisible: Boolean): VisualTransformation {
    if (passwordVisible) {
        return VisualTransformation.None
    }
    return PasswordVisualTransformation()
}

fun togglePasswordVisibility(passwordVisible: MutableState<Boolean>) {
    passwordVisible.value = !passwordVisible.value
}