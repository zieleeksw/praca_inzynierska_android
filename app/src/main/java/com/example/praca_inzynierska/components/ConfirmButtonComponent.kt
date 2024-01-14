package com.example.praca_inzynierska.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.R

@Composable
fun ConfirmButtonComponent(
    text: String,
    width: Dp,
    onClick: () -> Unit
) {

    val focusManager = LocalFocusManager.current

    Button(
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent),
        onClick = {
            focusManager.clearFocus()
            onClick()
        },
        modifier = Modifier
            .heightIn(48.dp)
            .padding(24.dp),
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .width(width)
                .heightIn(48.dp)
                .background(
                    shape = RoundedCornerShape(50.dp),
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.secondary_color),
                            colorResource(id = R.color.primary_color)
                        )
                    ),
                )
        ) {
            Text(
                text = text,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}