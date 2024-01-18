package com.example.praca_inzynierska.components.food.components.add

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.praca_inzynierska.R

@Composable
fun AddFoodButton(onClick: () -> Unit) {
    Button(
        onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(
            containerColor = colorResource(id = R.color.secondary_color),
            contentColor = Color.White
        )
    ) {
        Icon(
            Icons.Default.Add,
            contentDescription = "Add Icon",
            tint = Color.White
        )
    }
}