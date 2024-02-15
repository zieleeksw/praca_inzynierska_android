package com.example.praca_inzynierska.nutrition.composables.food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.nutrition.composables.commons.FoodNutritionInfoLabel
import com.example.praca_inzynierska.nutrition.vm.FoodScreenViewModel

@Composable
fun FoodCard(
    cardTitle: String,
    viewModel: FoodScreenViewModel,
    navController: NavHostController
) {

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = Ui.DEFAULT_CARD_ELEVATION),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
        ) {
            Column {
                Text(
                    text = cardTitle,
                    style = TextStyle(
                        color = Color.Gray,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    ),
                )
                FoodNutritionInfoLabel(
                    viewModel.calculateKcal(cardTitle).toString(),
                    viewModel.calculateProtein(cardTitle).toString(),
                    viewModel.calculateCarbs(cardTitle).toString(),
                    viewModel.calculateFat(cardTitle).toString()
                )
            }
            IconButton(
                onClick = {
                    navController.navigate(
                        "${Screens.PickFoodScreen.name}/${viewModel.date.value}/${cardTitle}"
                    )
                },
                content = {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add Icon",
                        tint = Color.White,
                        modifier = Modifier
                            .background(
                                color = colorResource(id = R.color.secondary_color),
                                shape = CircleShape
                            )
                    )
                }
            )
        }
    }
    val foodList = viewModel.filterFoodByMeal(cardTitle)
    foodList.forEach { food -> FoodCardItemComponent(food, viewModel) }
}