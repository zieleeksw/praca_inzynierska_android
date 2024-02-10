package com.example.praca_inzynierska.commons.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable

@Composable
fun AddIconButton(onClick: () -> Unit) {
    IconButton(onClick = { onClick() }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = null)
    }
}