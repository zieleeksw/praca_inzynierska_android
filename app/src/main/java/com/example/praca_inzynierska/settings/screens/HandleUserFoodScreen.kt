package com.example.praca_inzynierska.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.AddIconButton
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.settings.components.handle_food.CreateFoodDialog
import com.example.praca_inzynierska.settings.components.handle_food.CreateUserFoodScreenContent
import com.example.praca_inzynierska.settings.vm.HandleUserFoodScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HandleUserFoodScreen(
    navController: NavHostController
) {

    val viewModel = viewModel<HandleUserFoodScreenViewModel>()
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        CreateFoodDialog(
            viewModel = viewModel,
            onDismiss = {
                showDialog = false
                viewModel.onDismiss()
            },
        )
    }

    LaunchedEffect(Unit) {
        viewModel.fetchUserFood()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Add own food !",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color),
                    actionIconContentColor = Color.White
                ),
                actions = {
                    AddIconButton { showDialog = true }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,
                            tint = Color.White
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            ResourceStateHandler(
                resourceState = viewModel.userFoodState.value,
                content = { CreateUserFoodScreenContent(viewModel) }
            )
        }
    }
}