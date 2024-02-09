package com.example.praca_inzynierska.components.home.components.posts

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.data.Post
import com.example.praca_inzynierska.view.models.post.HomeScreenViewModel

@Composable
fun PostSectionComponent(
    navController: NavHostController,
    posts: List<Post>,
    paddingValues: PaddingValues,
    viewModel: HomeScreenViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .background(color = colorResource(id = R.color.light_gray))
    ) {
        items(posts.reversed()) { post ->
            PostItemComponent(
                navController,
                post = post,
                viewModel
            )
            Spacer(Modifier.height(2.dp))
        }
    }
}