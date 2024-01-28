package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.components.DeleteDialog
import com.example.praca_inzynierska.data.Food
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

@Composable
fun ExpandableCardItemComponent(
    food: Food,
    viewModel: FoodScreenViewModel
) {

    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            var showDialog by remember { mutableStateOf(false) }

            if (showDialog) {
                DeleteDialog(
                    deleteString = "Post",
                    onDismissRequest = {
                        showDialog = false
                    },
                    onConfirmation = {
                        viewModel.deleteFood(food.id)
                        showDialog = false
                    })
            }
            Divider(color = Color.White, thickness = 1.dp)
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${food.productName}  ${food.grams}g",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                IconButton(
                    onClick = {
                        showDialog = true
                    },
                    modifier = Modifier
                        .size(20.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            }
            Row(
                modifier = Modifier
                    .widthIn(260.dp)
                    .padding(horizontal = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${food.kcal}kcal",
                    style = TextStyle(color = Color.White)
                )

                Text(
                    text = "${food.proteins}",
                    style = TextStyle(color = Color.White)
                )
                Text(
                    text = "${food.carbs}",
                    style = TextStyle(color = Color.White)
                )
                Text(
                    text = "${food.fat}",
                    style = TextStyle(color = Color.White)
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpandableCardItemComponentPreview() {
    //  ExpandableCardItemComponent()
}