package com.example.praca_inzynierska.components.food.components.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.food.components.add.AddButtonAndExpandableButtons
import com.example.praca_inzynierska.helpers.FoodValuesCalculator
import com.example.praca_inzynierska.view.models.food.FoodScreenViewModel

@Composable
fun ExpandableCard(
    cardTitle: String,
    viewModel: FoodScreenViewModel,
    date: String,
    navController: NavHostController
) {

    var expanded by remember { mutableStateOf(false) }
    val calculator = FoodValuesCalculator()

    Card(
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.primary_color)),
        shape = AbsoluteCutCornerShape(0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = cardTitle,
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 20.sp,
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
                            color = Color.White
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
                            color = Color.White
                        )
                    )
                    Text(
                        text = "C ${
                            calculator.calculateCarbs(
                                cardTitle,
                                viewModel.foodState.value.list
                            )
                        }", style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = "F ${
                            calculator.calculateFat(
                                cardTitle,
                                viewModel.foodState.value.list
                            )
                        }", style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
            AddButtonAndExpandableButtons(
                expanded = expanded,
                onToggleExpanded = { expanded = !expanded },
                navController = navController,
                date = date,
                meal = cardTitle
            )
        }
        ExpandableContent(
            expanded,
            calculator.filterFoodByMeal(cardTitle, viewModel.foodState.value.list),
            viewModel
        )
    }
}