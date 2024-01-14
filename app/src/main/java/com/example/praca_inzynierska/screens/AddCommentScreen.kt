package com.example.praca_inzynierska.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.components.home.components.CustomTopAppBar
import com.example.praca_inzynierska.components.home.components.comments.AddCommentTextField
import com.example.praca_inzynierska.components.home.components.comments.CommentSectionComponent
import com.example.praca_inzynierska.data.Comment

@Composable
fun AddCommentScreen(
    navController: NavHostController
) {
    // TODO: implement viewmodel and delete mock data
    var comments by remember {
        mutableStateOf(
            listOf(
                Comment("User1", "Great post!"),
                Comment("User2", "Thanks for sharing."),
                Comment("User3", "Interesting topic."),
                Comment("User1", "Great post!"),
                Comment("User2", "Thanks for sharing."),
                Comment("User3", "Interesting topic."),
                Comment("User3", "Interesting topic."),
                Comment("User1", "Great post!"),
                Comment("User2", "Thanks for sharing."),
                Comment("User3", "Interesting topic."),
                Comment("User1", "Great post!"),
                Comment("User2", "Thanks for sharing.")
            )
        )
    }
    Scaffold(
        topBar = { CustomTopAppBar(navController = navController, text = "Add a comment!") },
        bottomBar = { AddCommentTextField() })
    {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            CommentSectionComponent(
                comments = comments
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddCommentScreenPreview() {
    val navController = rememberNavController()
    AddCommentScreen(navController)
}