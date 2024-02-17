package com.example.praca_inzynierska.settings.components.handle_food

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.ErrorTextComponent
import com.example.praca_inzynierska.settings.vm.HandleUserFoodScreenViewModel

@Composable
fun FoodNameTextFieldComponent(
    viewModel: HandleUserFoodScreenViewModel
) {
    Column {
        TextField(
            value = viewModel.foodState.name,
            onValueChange = { viewModel.onNameChanged(it) },
            isError = viewModel.foodState.onError != null,
            label = { Text("ProductName") },
            modifier = Modifier
                .fillMaxWidth(),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                disabledContainerColor = Color.White,
                errorContainerColor = Color.White,
                disabledIndicatorColor = Color.LightGray,
                unfocusedIndicatorColor = Color.LightGray,
                focusedIndicatorColor = colorResource(id = R.color.secondary_color),
                unfocusedTextColor = Color.Black,
                focusedTextColor = Color.Black,
                disabledTextColor = Color.Black,
                cursorColor = Color.Black
            )
        )
        ErrorTextComponent(viewModel.foodState.onError, 0, 0)
    }
}