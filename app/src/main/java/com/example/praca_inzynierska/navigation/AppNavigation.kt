package com.example.praca_inzynierska.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praca_inzynierska.R
import com.example.praca_inzynierska.screens.AddPostScreen
import com.example.praca_inzynierska.screens.AddProductScreen
import com.example.praca_inzynierska.screens.CommentsScreen
import com.example.praca_inzynierska.screens.DietConfigurationScreen
import com.example.praca_inzynierska.screens.FoodScreen
import com.example.praca_inzynierska.screens.HandleProductScreen
import com.example.praca_inzynierska.screens.HomeScreen
import com.example.praca_inzynierska.screens.LoginScreen
import com.example.praca_inzynierska.screens.RegisterScreen
import com.example.praca_inzynierska.screens.Screens
import com.example.praca_inzynierska.screens.SettingsScreen
import com.example.praca_inzynierska.training.screens.HandleExerciseScreen
import com.example.praca_inzynierska.training.screens.PickExerciseScreen
import com.example.praca_inzynierska.training.screens.TrainingScreen

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
            route = Screens.DietConfigurationScreen.name
        ) {
            DietConfigurationScreen(authNavController)
        }
        composable(route = Screens.MainContent.name) {
            MainContent()
        }
    }
}

@Composable
fun MainContent(
) {

    val mainNavController = rememberNavController()
    val navBackStackEntry by mainNavController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = colorResource(id = R.color.primary_color),
            ) {
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
                                contentDescription = null,
                                tint = Color.White,
                            )
                        },
                        label = {
                            Text(
                                text = navigationItem.label,
                                color = Color.White
                            )
                        },
                        colors = NavigationBarItemDefaults.colors(
                            indicatorColor = colorResource(id = R.color.secondary_color),
                        )
                    )
                }
            }
        },
    ) { paddingValues ->
        NavHost(
            navController = mainNavController,
            startDestination = Screens.HomeScreen.name,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(route = Screens.HomeScreen.name)
            {
                HomeScreen(mainNavController)
            }
            composable(route = Screens.AddPostScreen.name) {
                AddPostScreen(mainNavController)
            }
            composable(route = "${Screens.CommentsScreen.name}/{postId}",
                arguments = listOf(
                    navArgument("postId") {
                        type = NavType.LongType
                    }
                )
            ) { backStackEntry ->
                val postId = backStackEntry.arguments?.getLong("postId")
                CommentsScreen(mainNavController, postId!!)
            }
            composable(route = Screens.FoodScreen.name) {
                FoodScreen(mainNavController)
            }
            composable(route = "${Screens.AddProductScreen.name}/{date}/{meal}",
                arguments = listOf(
                    navArgument("date") {
                        type = NavType.StringType
                    },
                    navArgument("meal") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date")
                val meal = backStackEntry.arguments?.getString("meal")
                AddProductScreen(mainNavController, date!!, meal!!)
            }
            composable(route = Screens.ExerciseScreen.name) {
                TrainingScreen(mainNavController)
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen()
            }
            composable(
                route = "${Screens.PickExerciseScreen.name}/{date}",
                arguments = listOf(
                    navArgument("date") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date")
                PickExerciseScreen(mainNavController, date!!)
            }
            composable(route = "${Screens.HandleExerciseScreen.name}/{date}/{name}",
                arguments = listOf(
                    navArgument("date") {
                        type = NavType.StringType
                    },
                    navArgument("name") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date")
                val name = backStackEntry.arguments?.getString("name")
                HandleExerciseScreen(mainNavController, date!!, name!!)
            }
            composable(route = "${Screens.HandleProductScreen.name}/{date}/{meal}/{foodName}",
                arguments = listOf(
                    navArgument("date") {
                        type = NavType.StringType
                    },
                    navArgument("meal") {
                        type = NavType.StringType
                    },
                    navArgument("foodName") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val date = backStackEntry.arguments?.getString("date")
                val meal = backStackEntry.arguments?.getString("meal")
                val foodName = backStackEntry.arguments?.getString("foodName")
                HandleProductScreen(mainNavController, date!!, meal!!, foodName!!)
            }
        }
    }
}
