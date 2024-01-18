package com.example.praca_inzynierska.components

import androidx.compose.foundation.background
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R

@Composable
fun DeleteDialog(
    deleteString: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
) {
    AlertDialog(
        title = {
            Text(
                text = "Do you want do delete ${deleteString}?",
                color = colorResource(id = R.color.primary_color)
            )
        },
        onDismissRequest = {
            onDismissRequest()
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirmation()
                }
            ) {
                Text(
                    "Confirm",
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onDismissRequest()
                }
            ) {
                Text(
                    text = "Dismiss",
                    color = colorResource(id = R.color.secondary_color)
                )
            }
        },
        modifier = Modifier.background(color = MaterialTheme.colorScheme.surface
        ,shape = RoundedCornerShape(28.dp)
        )


    )
}


@Composable
@Preview
private fun CustomDialogPreview() {
    DeleteDialog("something", {}, {})
}
