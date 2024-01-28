package com.example.praca_inzynierska.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.components.CustomCircularProgressIndicator
import com.example.praca_inzynierska.components.OnErrorComponent
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.components.home.components.comments.AddCommentTextField
import com.example.praca_inzynierska.components.home.components.comments.CommentSectionComponent
import com.example.praca_inzynierska.view.models.CommentsScreenViewModel

@Composable
fun CommentsScreen(
    navController: NavHostController,
    postId: Long
) {

    val viewModel = CommentsScreenViewModel(postId)
    val commentState = viewModel.commentState

    Scaffold(
        topBar = { CustomTopAppBar(text = "Comments!") { navController.navigate(Screens.HomeScreen.name) } },
        bottomBar = { AddCommentTextField(postId, viewModel) })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it),
        ) {

            when {
                commentState.value.loading ->
                    CustomCircularProgressIndicator()

                commentState.value.error != null ->
                    OnErrorComponent()

                else -> {
                    CommentSectionComponent(
                        commentState.value.list,
                        viewModel
                    )
                }
            }
        }
    }
}