package com.example.praca_inzynierska.commons.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.commons.components.DeleteDialog

@Composable
fun TimestampWithDeleteComponent(
    text: String,
    deleteButton: Boolean,
    deleteString: String,
    onConfirm: () -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = deleteString,
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                onConfirm()
                showDialog = false
            })
    }

    Row {
        Text(
            text = text,
            fontSize = 14.sp,
            color = Color.Gray,
        )
        Spacer(modifier = Modifier.width(2.dp))
        if (deleteButton) {
            IconButton(
                onClick = {
                    showDialog = true
                },
                modifier = Modifier
                    .size(20.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Delete",
                    tint = Color.Gray
                )
            }
        }
    }
}