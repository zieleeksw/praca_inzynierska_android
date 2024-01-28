package com.example.praca_inzynierska.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.components.food.components.add.FoundedProductSectionComponent
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.requests.NutritionRequest
import com.example.praca_inzynierska.view.models.FetchFoodViewModel

@Composable
fun AddProductScreen(
    navController: NavHostController,
    date: String,
    meal: String
) {

    val viewModel = viewModel<FetchFoodViewModel>()
    var nutritionRequestList by remember { mutableStateOf(emptyList<NutritionRequest>()) }
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    nutritionRequestList = viewModel.nutritionState.value.list!!
                    Toast.makeText(
                        context, "Fetched successful",
                        Toast.LENGTH_LONG
                    ).show()
                }

                else ->
                    Toast.makeText(
                        context, "Something went wrong. Try again later",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(text = "Find and add Food!") { navController.navigate(Screens.FoodScreen.name) }
        },
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .padding(vertical = 8.dp, horizontal = 32.dp)
        ) {

            OutlinedTextField(
                value = viewModel.foodToSearchState,
                onValueChange = { foodToSearch -> viewModel.onFoodToSearchState(foodToSearch) },
                placeholder = { Text("Look for a product...") },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedBorderColor = colorResource(id = R.color.primary_color),
                    focusedBorderColor = colorResource(id = R.color.secondary_color),
                    focusedLabelColor = colorResource(id = R.color.secondary_color),
                    cursorColor = colorResource(id = R.color.secondary_color)
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp, horizontal = 32.dp),
                trailingIcon = {
                    IconButton(onClick = {
                        viewModel.fetchNutrition()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Search,
                            contentDescription = null,
                            tint = colorResource(id = R.color.primary_color)
                        )
                    }
                }
            )
            FoundedProductSectionComponent(nutritionRequestList, date, meal)
        }
    }
}

@Composable
@Preview
fun AddProductPreview() {
    AddProductScreen(navController = rememberNavController(), "", "")
}