package com.example.praca_inzynierska.commons.components

import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R

@Composable
fun SecondaryColorDivider() {
    Divider(color = colorResource(id = R.color.secondary_color), thickness = 1.dp)
}