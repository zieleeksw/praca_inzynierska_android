package com.example.praca_inzynierska.diet_configuration.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.diet_configuration.enums.Gender
import com.example.praca_inzynierska.diet_configuration.vm.DietConfigurationViewModel

@Composable
fun GenderSelectorComponent(
    viewModel: DietConfigurationViewModel
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(horizontal = 16.dp),
    ) {
        HeaderTextConfigurationScreenComponent(text = "Choose Your Gender")
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            GenderSelectorButtonComponent(
                gender = Gender.FEMALE,
                isSelected = viewModel.state.gender == Gender.FEMALE,
                onClick = { viewModel.onGenderChanged(Gender.FEMALE) }
            )
            Spacer(modifier = Modifier.width(128.dp))
            GenderSelectorButtonComponent(
                gender = Gender.MALE,
                isSelected = viewModel.state.gender == Gender.MALE,
                onClick = { viewModel.onGenderChanged(Gender.MALE) }
            )
        }
    }
}