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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.components.home.components.posts.ActionWithDropDownMenuComponent
import com.example.praca_inzynierska.components.home.components.posts.PostSectionComponent
import com.example.praca_inzynierska.data.Post

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(navController: NavHostController) {
    // TODO: Add buisness logic and remove these mock data
    val samplePosts = listOf(
        Post(
            "John Doe",
            "Lorem ipsum dolor sit amet. Lorem ipsum dolor sit ametLorem ipsum dolor sit ametLorem ipsum dolor sit amet",
            "2024-01-13 12:34"
        ),
        Post("Jane Smith", "Consectetur adipiscing elit.", "2024-01-13 14:45"),
        Post("John Doe", "Lorem ipsum dolor sit amet.", "2024-01-13 12:34"),
        Post("Jane Smith", "Consectetur adipiscing elit.", "2024-01-13 14:45"),
        Post("John Doe", "Lorem ipsum dolor sit amet.", "2024-01-13 12:34"),
        Post("Jane Smith", "Consectetur adipiscing elit.", "2024-01-13 14:45"),
    )

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
                    ActionWithDropDownMenuComponent()
                },
            )
        }
    ) {
        PostSectionComponent(navController, samplePosts, it)
    }
}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    val navController = rememberNavController()
    HomeScreen(navController)
}