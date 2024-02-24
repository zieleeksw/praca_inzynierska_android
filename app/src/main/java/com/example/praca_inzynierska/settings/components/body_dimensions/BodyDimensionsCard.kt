package com.example.praca_inzynierska.settings.components.body_dimensions

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.commons.components.DeleteDialog
import com.example.praca_inzynierska.commons.components.SecondaryColorDivider
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.settings.data.BodyDimensions

@Composable
fun BodyDimensionsCard(
    bodyDimensions: BodyDimensions,
    onDelete: (Long) -> Unit
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = "dimensions",
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                onDelete(bodyDimensions.id)
                showDialog = false
            })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Ui.DEFAULT_CARD_ELEVATION),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp)
            ) {
                Text(
                    text = bodyDimensions.date, fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start),
                )
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Close, contentDescription = "Delete")
                }
            }
            SecondaryColorDivider()
            Spacer(modifier = Modifier.height(6.dp))
            BodyDimensionsRow(name = "Arm", value = bodyDimensions.arm.toString())
            BodyDimensionsRow(name = "Chest", value = bodyDimensions.chest.toString())
            BodyDimensionsRow(name = "Waist", value = bodyDimensions.waist.toString())
            BodyDimensionsRow(name = "Thigh", value = bodyDimensions.leg.toString())
            BodyDimensionsRow(name = "Calf", value = bodyDimensions.calf.toString())
            Spacer(modifier = Modifier.height(6.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBodyDimensionsCard() {
    val sampleBodyDimensions = BodyDimensions(
        id = 1L,
        date = "2022-01-01",
        arm = 32,
        chest = 102,
        waist = 82,
        leg = 54,
        calf = 35
    )
    BodyDimensionsCard(bodyDimensions = sampleBodyDimensions, onDelete = {})
}

