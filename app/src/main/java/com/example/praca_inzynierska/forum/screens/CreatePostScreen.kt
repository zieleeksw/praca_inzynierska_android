package com.example.praca_inzynierska.forum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.CustomTopAppBar
import com.example.praca_inzynierska.forum.components.create_post.CreatePostButtonComponent
import com.example.praca_inzynierska.forum.components.create_post.CreatePostContentComponent
import com.example.praca_inzynierska.forum.vm.CreatePostViewModel

@Composable
fun CreatePostScreen(
    navController: NavHostController,
) {

    val viewModel = viewModel<CreatePostViewModel>()
    val focusManager = LocalFocusManager.current

    LaunchedEffect(viewModel.isAddedState) {
        if (viewModel.isAddedState) {
            navController.popBackStack()
        }
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Add a post!") { navController.popBackStack() } },
        bottomBar = {
            CreatePostButtonComponent { viewModel.onSubmit() }
        }) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onTap = { focusManager.clearFocus() })
                }
                .background(color = colorResource(id = R.color.light_gray))
        ) {
            CreatePostContentComponent(viewModel)
        }
    }
}