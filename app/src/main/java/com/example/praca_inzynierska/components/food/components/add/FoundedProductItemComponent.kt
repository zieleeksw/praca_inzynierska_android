package com.example.praca_inzynierska.components.food.components.add

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.components.ErrorTextComponent
import com.example.praca_inzynierska.data.AppFoodModel
import com.example.praca_inzynierska.view.models.food.AddFoodViewModel

@Composable
fun FoundedProductItemComponent(
    navController: NavHostController,
    date: String,
    meal: String,
    appFoodModel: AppFoodModel
) {

    val addFoodViewModel = remember { AddFoodViewModel() }
    onInit(addFoodViewModel, appFoodModel, date, meal)

    LaunchedEffect(addFoodViewModel.state) {
        val state = addFoodViewModel.state.isSuccessful
        if (state) {
            navController.navigate("FoodScreen")
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
            contentColor = Color.White
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .background(color = Color.White)
        ) {
            GramsTextFieldComponent(addFoodViewModel = addFoodViewModel)
            ErrorTextComponent(addFoodViewModel.state.error, 0, 0)
            Spacer(modifier = Modifier.height(8.dp))
            DescriptionValueComponent("Name", addFoodViewModel.state.productName)
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
    appFoodModel: AppFoodModel,
    date: String,
    meal: String
) {
    addFoodViewModel.initConstStateValues(
        date = date,
        meal = meal,
        name = appFoodModel.productName
    )
    onGramsChanged(
        addFoodViewModel,
        addFoodViewModel.state.grams,
        appFoodModel
    )
}

fun onGramsChanged(
    addFoodViewModel: AddFoodViewModel,
    grams: String,
    appFoodModel: AppFoodModel
) {
    addFoodViewModel.onGramsChanged(
        grams = grams,
        calories = appFoodModel.kcal.toString(),
        fat = appFoodModel.fat.toString(),
        carbs = appFoodModel.carbs.toString(),
        proteins = appFoodModel.proteins.toString()
    )
}