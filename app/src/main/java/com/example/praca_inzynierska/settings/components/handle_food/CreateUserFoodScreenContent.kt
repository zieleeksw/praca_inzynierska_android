package com.example.praca_inzynierska.settings.components.handle_food

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.praca_inzynierska.settings.vm.HandleUserFoodScreenViewModel

@Composable
fun CreateUserFoodScreenContent(
    viewModel: HandleUserFoodScreenViewModel
) {
    LazyColumn {
        items(
            items = viewModel.userFoodState.value.list,
            key = { it.productName }) { userFood ->
            UserFoodCard(userFood, viewModel)
        }
    }
}