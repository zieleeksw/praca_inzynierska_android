package com.example.praca_inzynierska.nutrition.composables.pick_food

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.praca_inzynierska.nutrition.composables.commons.FoodNutritionInfoLabel
import com.example.praca_inzynierska.nutrition.data.AppFoodModel

@Composable
fun FoodColumnItem(
    food: AppFoodModel,
    onFoodLabelClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onFoodLabelClick() }
            .padding(10.dp)
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = food.productName,
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center
                ),
            )

        }
        FoodNutritionInfoLabel(
            kcal = food.kcal.toString(),
            protein = food.proteins.toString(),
            carbs = food.carbs.toString(),
            fat = food.fat.toString()
        )
    }
    Divider()
}