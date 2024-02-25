package com.example.praca_inzynierska.settings.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.settings.components.body_dimensions.BodyDimensionsContent
import com.example.praca_inzynierska.settings.components.body_dimensions.CreateBodyDimensionsDialog
import com.example.praca_inzynierska.settings.vm.BodyDimensionsViewModel

@Composable
fun BodyDimensionsScreen(
    navController: NavHostController
) {

    val viewModel = viewModel<BodyDimensionsViewModel>()
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.fetchDimensions()
    }

    if (showDialog) {
        CreateBodyDimensionsDialog(
            viewModel = viewModel,
            onDismissRequest = { showDialog = false },
            onSave = {
                viewModel.addBodyDimensions { showDialog = false }
            }
        )
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Body dimensions") { navController.popBackStack() } }

    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(color = colorResource(id = R.color.light_gray)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ResourceStateHandler(
                resourceState = viewModel.bodyDimensions.value,
                content = { BodyDimensionsContent(viewModel) }
            )
        }
    }
}