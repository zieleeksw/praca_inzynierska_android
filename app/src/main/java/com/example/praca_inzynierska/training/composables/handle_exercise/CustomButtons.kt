package com.example.praca_inzynierska.training.composables.handle_exercise

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
fun CustomButtons(
    onSave: () -> Unit,
    onClear: () -> Unit
) {

    val secondaryColor = colorResource(id = R.color.secondary_color)

    Row {
        Button(
            onClick = { onSave() },
            colors = ButtonDefaults.buttonColors(containerColor = secondaryColor),
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "SAVE", color = Color.White
            )
        }
        Spacer(modifier = Modifier.width(8.dp))
        OutlinedButton(
            onClick = { onClear() },
            shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
            border = BorderStroke(1.dp, secondaryColor),
            modifier = Modifier.weight(1f),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.White,
                contentColor = secondaryColor
            )
        ) {
            Text(text = "CLEAR", color = secondaryColor)
        }
    }
}
