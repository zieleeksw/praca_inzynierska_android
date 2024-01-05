package com.example.praca_inzynierska.gender.choose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.enums.Gender

@Composable
fun GenderSelectorComponent() {

    var selectedGender by remember { mutableStateOf(Gender.FEMALE) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp),
    ) {
        HeaderTextConfigurationScreenComponent(text = "Choose Your Gender")
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
        ) {
            GenderSelectorButtonComponent(
                gender = Gender.FEMALE,
                isSelected = selectedGender == Gender.FEMALE,
                onClick = { selectedGender = Gender.FEMALE }
            )
            Spacer(modifier = Modifier.width(128.dp))
            GenderSelectorButtonComponent(
                gender = Gender.MALE,
                isSelected = selectedGender == Gender.MALE,
                onClick = { selectedGender = Gender.MALE }
            )
        }
    }
}