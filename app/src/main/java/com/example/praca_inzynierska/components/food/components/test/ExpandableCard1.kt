package com.example.praca_inzynierska.components.food.components.test

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.AbsoluteCutCornerShape
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.food.components.ExpandableContent
import com.example.praca_inzynierska.data.Food
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.view.models.food.CalculateValuesViewModel

@Composable
fun ExpandableCard1(
    cardTitle: String,
    food: List<Food>,
    date: String,
    navController: NavHostController
) {

    var expanded by remember { mutableStateOf(false) }
    val calculator = CalculateValuesViewModel()

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
                        text = "${calculator.calculateKcal(cardTitle, food)}kcal",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = "P ${calculator.calculateProtein(cardTitle, food)}",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = "C ${calculator.calculateCarbs(cardTitle, food)}", style = TextStyle(
                            color = Color.White
                        )
                    )
                    Text(
                        text = "F ${calculator.calculateFat(cardTitle, food)}", style = TextStyle(
                            color = Color.White
                        )
                    )
                }
            }
            AddButtonAndExpandableButton(
                expanded = expanded,
                onToggleExpanded = { expanded = !expanded },
                navController = navController,
                date = date,
                meal = cardTitle
            )
        }
        ExpandableContent(
            expanded,
            calculator.filterFoodByMeal(cardTitle, food)
        )
    }
}

@Composable
fun AddButtonAndExpandableButton(
    expanded: Boolean,
    onToggleExpanded: () -> Unit,
    navController: NavHostController,
    meal: String,
    date: String
) {
    val transition = updateTransition(targetState = expanded, label = "trans")
    val iconRotationDeg by transition.animateFloat(label = "icon change") { state ->
        if (state) {
            0f
        } else {
            180f
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Add Icon",
            tint = Color.White,
            modifier = Modifier
                .background(
                    color = colorResource(id = R.color.secondary_color),
                    shape = CircleShape
                )
                .clickable {
                    navController.navigate(
                        "${Screens.AddProductScreen.name}/${date}/${meal}"
                    )
                }
        )
        Spacer(modifier = Modifier.width(4.dp))
        Icon(
            imageVector = Icons.Default.KeyboardArrowUp,
            contentDescription = null,
            modifier = Modifier
                .rotate(iconRotationDeg)
                .clickable { onToggleExpanded() },
            tint = Color.White
        )
    }
}

@Preview
@Composable
fun Preview() {
    val food = listOf(Food(1, "", "", "", "", 100, 100, 100, 100))
    ExpandableCard1("TITLE", food, "DATE", rememberNavController())
}