package com.example.praca_inzynierska.components.food.components.add

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.ValidationEvent
import com.example.praca_inzynierska.requests.NutritionRequest
import com.example.praca_inzynierska.view.models.food.AddFoodViewModel

@Composable
fun FoundedProductItemComponent(
    nutritionRequest: NutritionRequest,
    date: String,
    meal: String
) {

    val addFoodViewModel = remember { AddFoodViewModel() }
    onInit(addFoodViewModel, nutritionRequest, date, meal)
    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        addFoodViewModel.validationEvents.collect { event ->
            when (event) {
                is ValidationEvent.Success -> {
                    Toast.makeText(
                        context, "Successfully added food",
                        Toast.LENGTH_LONG
                    ).show()
                }

                is ValidationEvent.BadCredentials ->
                    Toast.makeText(
                        context, "You have to enter grams",
                        Toast.LENGTH_LONG
                    ).show()

                is ValidationEvent.Failure ->
                    Toast.makeText(
                        context, "Something went wrong. Try again later",
                        Toast.LENGTH_LONG
                    ).show()
            }
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.primary_color),
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .background(color = colorResource(id = R.color.primary_color))
        ) {
            GramsTextFieldComponent(addFoodViewModel = addFoodViewModel)
            DescriptionValueComponent("Product Name", addFoodViewModel.state.productName)
            DescriptionValueComponent("Kcal", addFoodViewModel.state.kcal)
            DescriptionValueComponent("Fat", addFoodViewModel.state.fat)
            DescriptionValueComponent("Carbs", addFoodViewModel.state.carbs)
            DescriptionValueComponent("Proteins", addFoodViewModel.state.proteins)
            AddFoodButton { addFoodViewModel.onSubmit() }
        }
    }
}

fun onInit(
    addFoodViewModel: AddFoodViewModel,
    nutritionRequest: NutritionRequest,
    date: String,
    meal: String
) {
    addFoodViewModel.initConstStateValues(
        date = date,
        meal = meal,
        name = nutritionRequest.name
    )
    onGramsChanged(
        addFoodViewModel,
        addFoodViewModel.state.grams,
        nutritionRequest
    )
}

fun onGramsChanged(
    addFoodViewModel: AddFoodViewModel,
    grams: String,
    nutritionRequest: NutritionRequest
) {
    addFoodViewModel.onGramsChanged(
        grams = grams,
        calories = nutritionRequest.calories.toInt(),
        fat = nutritionRequest.fat_total_g.toInt(),
        carbs = nutritionRequest.carbohydrates_total_g.toInt(),
        proteins = nutritionRequest.protein_g.toInt()
    )
}