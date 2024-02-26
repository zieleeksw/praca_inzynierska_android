package com.example.praca_inzynierska.forum.components.create_post

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui

@Composable
fun CreatePostButtonComponent(
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = Modifier
            .fillMaxWidth()
            .background(colorResource(id = R.color.light_gray))
            .padding(vertical = 24.dp, horizontal = 16.dp),
        onClick = { onClick() },
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
        border = BorderStroke(1.dp, colorResource(id = R.color.secondary_color)),
        elevation = ButtonDefaults.elevatedButtonElevation(defaultElevation = Ui.DEFAULT_CARD_ELEVATION),
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = colorResource(id = R.color.secondary_color)
        )
    ) {
        Text(
            text = "Post",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}