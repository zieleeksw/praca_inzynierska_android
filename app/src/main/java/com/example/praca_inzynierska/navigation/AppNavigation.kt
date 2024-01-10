package com.example.praca_inzynierska.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praca_inzynierska.screens.DietConfigurationScreen
import com.example.praca_inzynierska.screens.FoodScreen
import com.example.praca_inzynierska.screens.HomeScreen
import com.example.praca_inzynierska.screens.LoginScreen
import com.example.praca_inzynierska.screens.RegisterScreen
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.screens.SettingsScreen
import com.example.praca_inzynierska.screens.TrainingScreen

@Composable
fun AppNavigation() {
    val authNavController = rememberNavController()
    NavHost(navController = authNavController, startDestination = Screens.LoginScreen.name) {
        composable(Screens.LoginScreen.name) {
            LoginScreen(authNavController)
        }
        composable(Screens.RegisterScreen.name) {
            RegisterScreen(authNavController)
        }
        composable(
            route = "${Screens.DietConfigurationScreen.name}/{userId}/{token}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.LongType
                },
                navArgument("token") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getLong("userId")
            val token = backStackEntry.arguments?.getString("token")
            DietConfigurationScreen(authNavController, userId, token)
        }
        composable("main") {
            MainContent()
        }
    }
}

@Composable
fun MainContent() {
    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    Scaffold(bottomBar = {
        NavigationBar {
            listOfNavItems.forEach { navigationItem ->
                NavigationBarItem(
                    selected = currentDestination?.hierarchy?.any { it.route == navigationItem.route } == true,
                    onClick = {
                        mainNavController.navigate(navigationItem.route) {
                            popUpTo(mainNavController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    icon = {
                        Icon(
                            imageVector = navigationItem.icon,
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(text = navigationItem.label)
                    }
                )
            }
        }
    }) { paddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.HomeScreen.name) {
                HomeScreen()
            }
            composable(route = Screens.FoodScreen.name) {
                FoodScreen()
            }
            composable(route = Screens.ExerciseScreen.name) {
                TrainingScreen()
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen()
            }
        }
    }
}