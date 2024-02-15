package com.example.praca_inzynierska.settings.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui

@Composable
fun CustomOutlinedButton(
    text: String,
    onClick: () -> Unit
) {
    val buttonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = Color.White,
        contentColor = colorResource(id = R.color.secondary_color)
    )
    val buttonBorder = BorderStroke(
        1.dp, colorResource(id = R.color.secondary_color)
    )
    val buttonShape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE
    val buttonElevation = ButtonDefaults.buttonElevation(
        defaultElevation = Ui.DEFAULT_CARD_ELEVATION
    )

    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        onClick = onClick,
        shape = buttonShape,
        border = buttonBorder,
        colors = buttonColors,
        elevation = buttonElevation
    ) {
        Text(text = text, style = MaterialTheme.typography.headlineSmall)
    }
}