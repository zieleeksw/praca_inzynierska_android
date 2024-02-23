package com.example.praca_inzynierska.settings.components.exercise_chart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LockedFieldsComponents(
    leftLabelTitle: String,
    leftLabelValue: String,
    rightLabelTitle: String,
    rightLabelValue: String
) {

    Column {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                StatsTextField(leftLabelValue, leftLabelTitle)
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                StatsTextField(rightLabelValue, rightLabelTitle)
            }
        }
    }
}