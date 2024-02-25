package com.example.praca_inzynierska.settings.components.body_dimensions

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.settings.vm.BodyDimensionsViewModel

@Composable
fun CreateBodyDimensionsDialog(
    viewModel: BodyDimensionsViewModel,
    onDismissRequest: () -> Unit,
    onSave: () -> Unit
) {

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
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
                BodyDimensionInputField(
                    "Arm (cm)",
                    viewModel.bodyDimensionsState.arm,
                    { viewModel.onArmChanged(it) },
                    viewModel.bodyDimensionsState.armError
                )
                BodyDimensionInputField(
                    "Chest (cm)",
                    viewModel.bodyDimensionsState.chest,
                    { viewModel.onChestChanged(it) },
                    viewModel.bodyDimensionsState.chestError
                )
                BodyDimensionInputField(
                    "Waist (cm)",
                    viewModel.bodyDimensionsState.waist,
                    { viewModel.onWaistChanged(it) },
                    viewModel.bodyDimensionsState.waistError
                )
                BodyDimensionInputField(
                    "Leg (cm)",
                    viewModel.bodyDimensionsState.leg,
                    { viewModel.onLegChanged(it) },
                    viewModel.bodyDimensionsState.legError
                )
                BodyDimensionInputField(
                    "Calf (cm)",
                    viewModel.bodyDimensionsState.calf,
                    { viewModel.onCalfChanged(it) },
                    viewModel.bodyDimensionsState.calfError
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text(text = "Dismiss", color = colorResource(id = R.color.secondary_color))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = { onSave() })
                    {
                        Text("OK", color = colorResource(id = R.color.secondary_color))
                    }
                }
            }
        }
    }
}


