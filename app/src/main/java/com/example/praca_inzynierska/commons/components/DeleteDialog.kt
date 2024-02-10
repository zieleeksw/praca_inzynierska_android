package com.example.praca_inzynierska.commons.components

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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.praca_inzynierska.R

@Composable
fun DeleteDialog(
    deleteString: String,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
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
                        text = "Delete",
                        color = Color.White
                    )
                }
                Text(
                    modifier = Modifier.padding(12.dp),
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                    text = "Do you want to delete $deleteString?",
                    color = Color.Black,
                )
                Spacer(modifier = Modifier.height(24.dp))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(onClick = onDismissRequest) {
                        Text("Dismiss", color = colorResource(id = R.color.secondary_color))
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    TextButton(onClick = onConfirmation) {
                        Text("OK", color = colorResource(id = R.color.secondary_color))
                    }
                }
            }
        }
    }
}


@Composable
@Preview
private fun CustomDialogPreview() {
    DeleteDialog("something", {}, {})
}
