package com.example.praca_inzynierska.diet_configuration.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.ErrorTextComponent
import com.example.praca_inzynierska.diet_configuration.vm.DietConfigurationViewModel

@Composable
fun WeightRowComponent(
    viewModel: DietConfigurationViewModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderTextConfigurationScreenComponent(text = "Current")
            OutlinedTextField(
                value = viewModel.state.currentWeight,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                onValueChange = { weight -> viewModel.onCurrentWeightChanged(weight.take(5)) },
                shape = MaterialTheme.shapes.medium,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = viewModel.state.currentWeightError != null,
                modifier = Modifier
                    .height(58.dp)
                    .width(124.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = R.color.primary_color),
                    focusedBorderColor = colorResource(id = R.color.secondary_color),
                ),
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "KG",
                        style = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                },
            )
            ErrorTextComponent(error = viewModel.state.currentWeightError, start = 0, end = 0)
        }
        Spacer(modifier = Modifier.width(8.dp))
        Column(
            modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HeaderTextConfigurationScreenComponent(text = "Target")
            OutlinedTextField(
                value = viewModel.state.targetWeight,
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                onValueChange = { weight -> viewModel.onTargetWeightChanged(weight.take(5)) },
                shape = MaterialTheme.shapes.medium,
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                isError = viewModel.state.targetWeightError != null,
                modifier = Modifier
                    .height(58.dp)
                    .width(124.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = R.color.primary_color),
                    focusedBorderColor = colorResource(id = R.color.secondary_color),
                ),
                label = {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "KG",
                        style = LocalTextStyle.current.copy(textAlign = TextAlign.Center)
                    )
                },
            )
            ErrorTextComponent(error = viewModel.state.targetWeightError, start = 0, end = 0)
        }
    }
}

@Composable
@Preview
fun WeighPrev() {
    WeightRowComponent(viewModel = DietConfigurationViewModel())
}