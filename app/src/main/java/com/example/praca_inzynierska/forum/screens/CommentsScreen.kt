package com.example.praca_inzynierska.forum.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.commons.components.resource_loaders.ResourceStateHandler
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.forum.components.comments.AddCommentTextField
import com.example.praca_inzynierska.forum.components.comments.CommentSectionComponent
import com.example.praca_inzynierska.forum.vm.CommentsScreenViewModel
import com.example.praca_inzynierska.screens.Screens

@Composable
fun CommentsScreen(
    navController: NavHostController,
    postId: Long
) {

    val viewModel = viewModel<CommentsScreenViewModel>()

    LaunchedEffect(Unit) {
        viewModel.fetchComments(postId)
    }

    Scaffold(
        topBar = { CustomTopAppBar(text = "Comments!") { navController.navigate(Screens.HomeScreen.name) } },
        bottomBar = { AddCommentTextField(postId, viewModel) })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(color = colorResource(id = R.color.light_gray)),
        ) {
            ResourceStateHandler(resourceState = viewModel.commentState.value) {
                CommentSectionComponent(viewModel, postId)
            }
        }
    }
}