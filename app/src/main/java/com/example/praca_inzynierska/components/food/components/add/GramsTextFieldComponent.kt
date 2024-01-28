package com.example.praca_inzynierska.components.food.components.add

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.view.models.food.AddFoodViewModel

@Composable
fun GramsTextFieldComponent(
    addFoodViewModel: AddFoodViewModel
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            value = addFoodViewModel.state.grams,
            onValueChange = {
                addFoodViewModel.onGramsChanged(
                    grams = it,
                    calories = addFoodViewModel.state.kcal.toInt(),
                    fat = addFoodViewModel.state.fat.toInt(),
                    carbs = addFoodViewModel.state.carbs.toInt(),
                    proteins = addFoodViewModel.state.proteins.toInt()
                )
            },
            modifier = Modifier
                .weight(1f)
                .height(50.dp)
                .padding(end = 8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            colors = OutlinedTextFieldDefaults.colors(
                unfocusedContainerColor = Color.White,
                focusedContainerColor = Color.White,
                focusedTextColor = colorResource(id = R.color.secondary_color),
                unfocusedTextColor = Color.Gray,
                unfocusedSupportingTextColor = Color.Gray,
                focusedBorderColor = colorResource(id = R.color.secondary_color),
                unfocusedBorderColor = Color.Gray,
                unfocusedLabelColor = Color.Gray,
                focusedLabelColor = colorResource(id = R.color.secondary_color)
            )
        )
        Text(
            text = "g",
            color = Color.White,
            modifier = Modifier
                .align(Alignment.CenterVertically)
        )
    }
}