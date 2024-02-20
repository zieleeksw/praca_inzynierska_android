package com.example.praca_inzynierska.settings.components.handle_food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.settings.vm.HandleUserFoodScreenViewModel

@Composable
fun CreateFoodDialog(
    viewModel: HandleUserFoodScreenViewModel,
    onDismiss: () -> Unit
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.White
        ) {
            Column(modifier = Modifier.padding(12.dp)) {
                FoodNameTextFieldComponent(viewModel)
                Spacer(modifier = Modifier.height(8.dp))
                NumberFoodComponent(
                    title = "kcal",
                    value = viewModel.foodState.kcal,
                    onValueChanged = viewModel::onKcalChanged,
                    onError = viewModel.foodState.onKcalError
                )
                NumberFoodComponent(
                    title = "fat",
                    value = viewModel.foodState.fat,
                    onValueChanged = viewModel::onFatChanged,
                    onError = viewModel.foodState.onFatError

                )
                NumberFoodComponent(
                    title = "carbs",
                    value = viewModel.foodState.carbs,
                    onValueChanged = viewModel::onCarbsChanged,
                    onError = viewModel.foodState.onCarbsError

                )
                NumberFoodComponent(
                    title = "protein",
                    value = viewModel.foodState.protein,
                    onValueChanged = viewModel::onProteinChanged,
                    onError = viewModel.foodState.onProteinError
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text("Dismiss", color = colorResource(id = R.color.secondary_color))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        viewModel.addFood {
                            onDismiss()
                        }
                    }) {
                        Text("OK", color = colorResource(id = R.color.secondary_color))
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun CreateFood() {
    CreateFoodDialog(viewModel = HandleUserFoodScreenViewModel()) {

    }
}