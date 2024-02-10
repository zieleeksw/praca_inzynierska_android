package com.example.praca_inzynierska.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.commons.components.SearchTextField
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.data.AppFoodModel
import com.example.praca_inzynierska.view.models.AddProductScreenViewModel

@Composable
fun AddProductScreen(
    navController: NavHostController,
    date: String,
    meal: String
) {

    val viewModel = viewModel<AddProductScreenViewModel>()
    val textState = remember { mutableStateOf(TextFieldValue("")) }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add food!") { navController.popBackStack() } },
    ) {

        val food = viewModel.foodState.value.list

        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchTextField(state = textState)
            LazyColumn {
                items(items = food.filter { food ->
                    food.productName.contains(textState.value.text, ignoreCase = true)
                }, key = { food -> food.productName }) { food ->
                    FoodColumnItem(
                        food = food
                    ) { navController.navigate("${Screens.HandleProductScreen.name}/${date}/${meal}/${food.productName}") }
                }
            }
        }
    }
}

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
        Row(
            modifier = Modifier
                .widthIn(260.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "${food.kcal}kcal",
                style = TextStyle(
                    color = Color.Gray
                )
            )
            Text(
                text = "P ${food.proteins}",
                style = TextStyle(
                    color = Color.Gray
                )
            )
            Text(
                text = "C ${food.carbs}", style = TextStyle(
                    color = Color.Gray
                )
            )
            Text(
                text = "F ${food.fat}", style = TextStyle(
                    color = Color.Gray
                )
            )
        }
    }
    Divider()
}