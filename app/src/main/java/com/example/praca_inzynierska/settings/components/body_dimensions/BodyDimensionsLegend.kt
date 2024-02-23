package com.example.praca_inzynierska.settings.components.body_dimensions

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui

@Composable
fun BodyDimensionsLegend() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 8.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(16.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.secondary_color))
                    .clip(shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE)
            )
        }
        Text(
            text = " - Weight",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))

        Box(
            modifier = Modifier
                .size(16.dp)
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = colorResource(id = R.color.primary_color))
                    .clip(shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE)
            )
        }
        Text(
            text = " - Repetition",
            fontSize = 12.sp,
            modifier = Modifier.padding(top = 4.dp)
        )
    }
}