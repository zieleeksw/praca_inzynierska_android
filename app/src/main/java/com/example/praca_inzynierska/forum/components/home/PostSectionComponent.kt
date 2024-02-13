package com.example.praca_inzynierska.forum.components.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.praca_inzynierska.forum.vm.HomeScreenViewModel

@Composable
fun PostSectionComponent(
    navController: NavHostController,
    viewModel: HomeScreenViewModel
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        items(viewModel.filteredPosts.value.reversed()) { post ->
            PostItemComponent(
                navController = navController,
                post = post,
                viewModel = viewModel
            )
            Spacer(Modifier.height(2.dp))
        }
    }
}