package com.example.praca_inzynierska.components.diet_configuration

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.commons.components.ErrorTextComponent
import com.example.praca_inzynierska.view.models.DietConfigurationViewModel

@Composable
fun DateSelectorWithErrorComponent(
    viewModel: DietConfigurationViewModel
) {
    DateSelectorComponent(viewModel)
    ErrorTextComponent(viewModel.state.dateOfBirthError, 0, 0)
}

@Composable
fun DateSelectorComponent(
    viewModel: DietConfigurationViewModel
) {

    val state = viewModel.state

    Column(
        modifier = Modifier
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HeaderTextConfigurationScreenComponent(text = "Choose your date of birth")
        Row(
            modifier = Modifier
                .padding(bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            DietConfigurationScreenTextField(
                numOfTakenCharacters = 2,
                textFieldValue = "DD",
                width = 63.dp,
                value = state.dayOfBirth,
                isError = state.dateOfBirthError != null,
                onTextFieldChanged = { day -> viewModel.onDayOfBirthChanged(day) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DietConfigurationScreenTextField(
                numOfTakenCharacters = 2,
                textFieldValue = "MM",
                width = 69.dp,
                value = state.monthOfBirth,
                isError = state.dateOfBirthError != null,
                onTextFieldChanged = { month -> viewModel.onMonthOfBirthChanged(month) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            DietConfigurationScreenTextField(
                numOfTakenCharacters = 4,
                textFieldValue = "YYYY",
                width = 82.dp,
                value = state.yearOfBirth,
                isError = state.dateOfBirthError != null,
                onTextFieldChanged = { year -> viewModel.onYearOfBirthChanged(year) }
            )
        }
    }
}
