package com.example.praca_inzynierska.screens

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.Global
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.CustomCircularProgressIndicator
import com.example.praca_inzynierska.components.OnFetchDataErrorComponent
import com.example.praca_inzynierska.components.home.components.posts.ActionWithDropDownMenuComponent
import com.example.praca_inzynierska.components.home.components.posts.PostSectionComponent
import com.example.praca_inzynierska.view.models.post.HomeScreenViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
) {

    val homeViewModel = HomeScreenViewModel()
    val postState = homeViewModel.postState
    val selectedFilter = remember { mutableStateOf<String?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Welcome!",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = topAppBarColors(
                    containerColor = colorResource(id = R.color.primary_color),
                    actionIconContentColor = Color.White
                ),
                actions = {
                    IconButton(onClick = { navController.navigate(Screens.AddPostScreen.name) }) {
                        Icon(imageVector = Icons.Default.Add, contentDescription = null)
                    }
                    ActionWithDropDownMenuComponent {
                        selectedFilter.value = it
                    }
                },
            )
        }
    ) {

        when {
            postState.value.loading ->
                CustomCircularProgressIndicator()

            postState.value.error != null -> {
                OnFetchDataErrorComponent()
            }

            else -> {
                val filteredPosts = when (selectedFilter.value) {
                    "Followed" -> postState.value.list.filter { post ->
                        post.followers.contains(Global.currentUserId)
                    }

                    "Newest" -> {
                        postState.value.list
                    }

                    "Oldest" -> {
                        postState.value.list.asReversed()
                    }

                    "Mine" -> postState.value.list.filter { post ->
                        post.authorId == Global.currentUserId
                    }

                    else -> postState.value.list
                }
                PostSectionComponent(navController, filteredPosts, it, homeViewModel)
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}