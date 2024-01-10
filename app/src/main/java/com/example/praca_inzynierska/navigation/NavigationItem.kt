package com.example.praca_inzynierska.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Fastfood
import androidx.compose.material.icons.filled.FitnessCenter
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.praca_inzynierska.screens.Screens

data class NavigationItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

val listOfNavItems = listOf(
    NavigationItem(
        label = "Home",
        icon = Icons.Default.Home,
        route = Screens.HomeScreen.name
    ),
    NavigationItem(
        label = "Food",
        icon = Icons.Default.Fastfood,
        route = Screens.FoodScreen.name
    ),
    NavigationItem(
        label = "Exercise",
        icon = Icons.Default.FitnessCenter,
        route = Screens.ExerciseScreen.name
    ),
    NavigationItem(
        label = "Settings",
        icon = Icons.Default.Settings,
        route = Screens.SettingsScreen.name
    )
)