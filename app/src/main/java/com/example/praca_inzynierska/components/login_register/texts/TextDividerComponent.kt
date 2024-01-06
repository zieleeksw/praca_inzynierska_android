package com.example.praca_inzynierska.components.login_register.texts

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DividerTextComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp)
                .weight(1f),

            )
        Text(modifier = Modifier.padding(8.dp), text = "or", fontSize = 14.sp, color = Color.Black)
        Divider(
            thickness = 1.dp,
            color = Color.Gray,
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 8.dp)
                .weight(1f),
        )
    }
}