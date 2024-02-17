package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.commons.components.DeleteDialog
import com.example.praca_inzynierska.nutrition.composables.commons.FoodNutritionInfoLabel
import com.example.praca_inzynierska.nutrition.data.Food
import com.example.praca_inzynierska.nutrition.vm.FoodScreenViewModel

@Composable
fun FoodCardItemComponent(
    food: Food,
    viewModel: FoodScreenViewModel
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = "food",
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                viewModel.deleteFood(food.id)
                showDialog = false
            })
    }

    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp, end = 4.dp)
    ) {
        Column {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "${food.productName}  ${food.grams}g",
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )

            }
            FoodNutritionInfoLabel(
                food.kcal.toString(),
                food.proteins.toString(),
                food.carbs.toString(),
                food.fat.toString()
            )
        }
        IconButton(onClick = { showDialog = true }) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Delete",
                tint = Color.Gray
            )
        }
    }
}