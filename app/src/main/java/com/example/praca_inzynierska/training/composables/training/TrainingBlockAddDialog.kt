package com.example.praca_inzynierska.training.composables.training

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.settings.data.Training
import com.example.praca_inzynierska.training.vm.TrainingScreenViewModel

@Composable
fun TrainingBlockAddDialog(
    onDismiss: () -> Unit,
    onOk: (Long) -> Unit,
    viewModel: TrainingScreenViewModel
) {

    var selectedBlock by remember { mutableStateOf<Training?>(null) }

    Dialog(onDismissRequest = onDismiss) {
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
                        text = "Choose training block",
                        color = Color.White
                    )
                }

                LazyColumn {
                    items(viewModel.trainingState.value.list,
                        key = { it.id }) { block ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { selectedBlock = block }
                                .padding(12.dp)
                        ) {
                            RadioButton(
                                selected = block == selectedBlock,
                                onClick = { selectedBlock = block }
                            )
                            Text(
                                text = block.name,
                                style = MaterialTheme.typography.bodyMedium,
                                modifier = Modifier.padding(start = 8.dp)
                            )
                        }
                    }
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = { onDismiss() }) {
                        Text(text = "Dismiss", color = colorResource(id = R.color.secondary_color))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = {
                        selectedBlock?.id?.let { onOk(it) }
                    })
                    {
                        Text("OK", color = colorResource(id = R.color.secondary_color))
                    }
                }
            }
        }
    }
}