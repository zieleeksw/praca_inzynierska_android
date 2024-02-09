package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.helpers.FoodValuesCalculator
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

@Composable
fun FoodCard(
    cardTitle: String,
    viewModel: FoodScreenViewModel,
    date: String,
    navController: NavHostController
) {

    val calculator = FoodValuesCalculator()

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 4.dp
        ),
        shape = RoundedCornerShape(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
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
                Row(
                    modifier = Modifier.widthIn(260.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "${
                            calculator.calculateKcal(
                                cardTitle,
                                viewModel.foodState.value.list
                            )
                        }kcal",
                        style = TextStyle(
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = "P ${
                            calculator.calculateProtein(
                                cardTitle,
                                viewModel.foodState.value.list
                            )
                        }",
                        style = TextStyle(
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = "C ${
                            calculator.calculateCarbs(
                                cardTitle,
                                viewModel.foodState.value.list
                            )
                        }", style = TextStyle(
                            color = Color.Gray
                        )
                    )
                    Text(
                        text = "F ${
                            calculator.calculateFat(
                                cardTitle,
                                viewModel.foodState.value.list
                            )
                        }", style = TextStyle(
                            color = Color.Gray
                        )
                    )
                }
            }
            IconButton(
                onClick = {
                    navController.navigate(
                        "${Screens.AddProductScreen.name}/${date}/${cardTitle}"
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
    val foodList = calculator.filterFoodByMeal(cardTitle, viewModel.foodState.value.list)
    foodList.forEach { food ->
        FoodCardItemComponent(food, viewModel)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExpandableCard() {
    val mockNavController = rememberNavController()
    val mockViewModel = FoodScreenViewModel("2024-02-08")

    FoodCard(
        cardTitle = "Przykładowy Posiłek",
        viewModel = mockViewModel,
        date = "2024-02-08",
        navController = mockNavController
    )
}