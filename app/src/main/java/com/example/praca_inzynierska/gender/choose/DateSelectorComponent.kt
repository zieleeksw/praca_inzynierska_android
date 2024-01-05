package com.example.praca_inzynierska.gender.choose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DateSelectorComponent() {

    val day by remember { mutableStateOf("") }
    val month by remember { mutableStateOf("") }
    val year by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderTextConfigurationScreenComponent(text = "Choose your date of birth")
        Row(
            modifier = Modifier
                .padding(bottom = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {

            DietConfigurationScreenTextField(
                numOfTakenCharacters = 2,
                textFieldValue = "DD",
                width = 63.dp,
                value = day
            )
            DietConfigurationScreenTextField(
                numOfTakenCharacters = 2,
                textFieldValue = "MM",
                width = 69.dp,
                value = month
            )
            DietConfigurationScreenTextField(
                numOfTakenCharacters = 4,
                textFieldValue = "YYYY",
                width = 82.dp,
                value = year
            )
        }
    }
}