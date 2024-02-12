package com.example.praca_inzynierska.nutrition.composables.handle_product

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.nutrition.vm.HandleFoodScreenViewModel

@Composable
fun GramsTextFieldComponent(
    viewModel: HandleFoodScreenViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = viewModel.addState.grams,
            onValueChange = {
                val filteredInput =
                    it.filterNot { char -> char == '.' || char == ',' || char == '-' || char == ' ' }
                viewModel.onGramsChanged(grams = filteredInput)
            },
            isError = viewModel.foodByNameState.value.error != null,
            modifier = Modifier
                .weight(1f)
                .height(50.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
            ),
        )
        Text(
            text = "g",
            color = Color.Black,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}