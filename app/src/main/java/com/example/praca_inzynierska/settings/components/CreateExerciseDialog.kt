package com.example.praca_inzynierska.settings.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.ErrorTextComponent
import com.example.praca_inzynierska.settings.vm.CreateUserExerciseScreenViewModel

@Composable
fun CreateExerciseDialog(
    viewModel: CreateUserExerciseScreenViewModel,
    onDismissRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            color = Color.White
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(color = colorResource(id = R.color.secondary_color))
                        .padding(12.dp),
                    contentAlignment = Alignment.TopStart
                ) {
                    Text(
                        fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                        text = "Save",
                        color = Color.White
                    )
                }
                Text(
                    modifier = Modifier.padding(12.dp),
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize,
                    text = "Do you want to delete add exercise?",
                    color = Color.Black,
                )
                TextField(
                    value = viewModel.exerciseState.name,
                    onValueChange = { viewModel.onNameChanged(it) },
                    isError = viewModel.exerciseState.onError != null,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 8.dp)
                        .align(Alignment.CenterHorizontally),
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
                ErrorTextComponent(error = viewModel.exerciseState.onError, start = 8, end = 0)
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = "Dismiss", color = colorResource(id = R.color.secondary_color))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        viewModel.addExercise {
                            onDismissRequest()
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
fun Previeww() {
    CreateExerciseDialog(CreateUserExerciseScreenViewModel(), {})
}