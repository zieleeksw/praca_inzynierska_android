package com.example.praca_inzynierska.components.training.exercise

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp

@Composable
fun CustomText(
    kgs: String,
    unitOfMeasure: String,
    modifier: Modifier
) {
    val text = buildAnnotatedString {
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.Bold,
                color = Color.Gray,
                fontSize = 18.sp
            )
        ) {
            append("$kgs ")
        }
        withStyle(
            style = SpanStyle(
                fontWeight = FontWeight.ExtraLight,
                color = Color.Gray,
                fontSize = 18.sp
            )
        ) {
            append(unitOfMeasure)
        }
    }

    Text(
        modifier = modifier,
        text = text,
    )
}