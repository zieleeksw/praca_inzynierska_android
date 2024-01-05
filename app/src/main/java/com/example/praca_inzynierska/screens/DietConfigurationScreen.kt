package com.example.praca_inzynierska.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.gender.choose.DateSelectorComponent
import com.example.praca_inzynierska.gender.choose.EnterValueOutlinedTextField
import com.example.praca_inzynierska.gender.choose.GenderSelectorComponent
import com.example.praca_inzynierska.login.register.components.buttons.ConfirmButtonComponent

@Composable
fun DietConfigurationScreen() {

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .padding(28.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            GenderSelectorComponent()
            DateSelectorComponent()
            EnterValueOutlinedTextField(
                headerText = "Height",
                textFieldValue = "CM",
                numOfTakenCharacters = 3
            )
            EnterValueOutlinedTextField(
                headerText = "Current weight",
                textFieldValue = "KG",
                numOfTakenCharacters = 4
            )
            EnterValueOutlinedTextField(
                headerText = "Target weight",
                textFieldValue = "KG",
                numOfTakenCharacters = 4
            )
            ConfirmButtonComponent(text = "Next", 128.dp) {
            }
        }
    }
}

@Preview
@Composable
fun Priview() {
    DietConfigurationScreen()
}
