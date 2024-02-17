package com.example.praca_inzynierska.settings.components.handle_food

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.praca_inzynierska.commons.components.DeleteDialog
import com.example.praca_inzynierska.commons.components.SecondaryColorDivider
import com.example.praca_inzynierska.commons.objects.Ui
import com.example.praca_inzynierska.nutrition.composables.handle_product.FoodDescriptionValueComponent
import com.example.praca_inzynierska.nutrition.data.AppFoodModel
import com.example.praca_inzynierska.settings.vm.HandleUserFoodScreenViewModel

@Composable
fun UserFoodCard(
    appFoodModel: AppFoodModel,
    viewModel: HandleUserFoodScreenViewModel
) {

    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        DeleteDialog(
            deleteString = "Exercise",
            onDismissRequest = {
                showDialog = false
            },
            onConfirmation = {
                viewModel.deleteUserFoodByName(appFoodModel.productName)
                showDialog = false
            })
    }

    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.elevatedCardElevation(
            defaultElevation = Ui.DEFAULT_CARD_ELEVATION
        ),
        shape = Ui.DEFAULT_ROUNDED_CORNER_SHAPE
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color.White)
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp)
            ) {
                Text(
                    appFoodModel.productName,
                    color = Color.Black,
                    style = MaterialTheme.typography.titleMedium
                )
                IconButton(onClick = { showDialog = true }) {
                    Icon(Icons.Default.Close, contentDescription = "Delete")
                }
            }
            SecondaryColorDivider()
            Spacer(modifier = Modifier.height(8.dp))
            FoodDescriptionValueComponent("Kcal", appFoodModel.kcal.toString())
            FoodDescriptionValueComponent("Fat", appFoodModel.fat.toString())
            FoodDescriptionValueComponent("Carbs", appFoodModel.carbs.toString())
            FoodDescriptionValueComponent("Proteins", appFoodModel.proteins.toString())
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserFoodCard() {
    val sampleAppFoodModel = AppFoodModel(
        productName = "Apple",
        kcal = 52,
        fat = 0,
        carbs = 14,
        proteins = 0
    )
    UserFoodCard(appFoodModel = sampleAppFoodModel, viewModel = HandleUserFoodScreenViewModel())
}