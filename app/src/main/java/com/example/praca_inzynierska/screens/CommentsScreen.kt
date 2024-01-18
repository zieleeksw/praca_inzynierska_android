package com.example.praca_inzynierska.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.components.home.components.comments.AddCommentTextField
import com.example.praca_inzynierska.components.home.components.comments.CommentSectionComponent
import com.example.praca_inzynierska.view.models.comments.FetchCommentViewModel

@Composable
fun CommentsScreen(
    navController: NavHostController,
    postId: Long
) {

    val fetchCommentViewModel = FetchCommentViewModel(postId)
    val commentState = fetchCommentViewModel.commentState

    Scaffold(
        topBar = { CustomTopAppBar(navController = navController, text = "Add a comment!") },
        bottomBar = { AddCommentTextField(navController, postId) })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            when {
                commentState.value.loading ->
                    CircularProgressIndicator()

                commentState.value.error != null ->
                    Text("ERROR OCCURED")

                else -> {
                    CommentSectionComponent(
                        navController,
                        postId,
                        commentState.value.list
                    )
                }
            }
        }
    }
}