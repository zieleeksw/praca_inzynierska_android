package com.example.praca_inzynierska.commons.navigation

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
import com.example.praca_inzynierska.auth.screens.LoginScreen
import com.example.praca_inzynierska.auth.screens.RegisterScreen
import com.example.praca_inzynierska.commons.screens.Screens
import com.example.praca_inzynierska.diet_configuration.screens.DietConfigurationScreen
import com.example.praca_inzynierska.forum.screens.CommentsScreen
import com.example.praca_inzynierska.forum.screens.CreatePostScreen
import com.example.praca_inzynierska.forum.screens.HomeScreen
import com.example.praca_inzynierska.nutrition.screens.FoodScreen
import com.example.praca_inzynierska.nutrition.screens.HandleProductScreen
import com.example.praca_inzynierska.nutrition.screens.PickFoodScreen
import com.example.praca_inzynierska.settings.screens.BodyDimensionsScreen
import com.example.praca_inzynierska.settings.screens.HandleUserExercisesScreen
import com.example.praca_inzynierska.settings.screens.HandleUserFoodScreen
import com.example.praca_inzynierska.settings.screens.SettingsScreen
import com.example.praca_inzynierska.settings.screens.training.TrainingBlockExercisesScreen
import com.example.praca_inzynierska.settings.screens.training.TrainingBlockHandleExerciseScreen
import com.example.praca_inzynierska.settings.screens.training.TrainingBlockScreen
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
            composable(route = Screens.CreatePostScreen.name) {
                CreatePostScreen(mainNavController)
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
            composable(route = "${Screens.PickFoodScreen.name}/{date}/{meal}",
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
                PickFoodScreen(mainNavController, date!!, meal!!)
            }
            composable(route = Screens.ExerciseScreen.name) {
                TrainingScreen(mainNavController)
            }
            composable(route = Screens.SettingsScreen.name) {
                SettingsScreen(mainNavController)
            }
            composable(route = Screens.HandleUserExercisesScreen.name) {
                HandleUserExercisesScreen()
            }
            composable(route = Screens.HandleUserFoodScreen.name) {
                HandleUserFoodScreen()
            }
            composable(route = Screens.TrainingBlockScreen.name) {
                TrainingBlockScreen(mainNavController)
            }
            composable(route = Screens.BodyDimensionsScreen.name) {
                BodyDimensionsScreen()
            }
            composable(
                route = "${Screens.TrainingBlockExercisesScreen.name}/{trainingId}",
                arguments = listOf(
                    navArgument("trainingId") {
                        type = NavType.LongType
                    }
                )
            ) { backstackEntry ->
                val trainingId = backstackEntry.arguments?.getLong("trainingId")
                TrainingBlockExercisesScreen(mainNavController, trainingId!!)
            }
            composable(route = "${Screens.TrainingBlockHandleExerciseScreen.name}/{trainingId}/{name}",
                arguments = listOf(
                    navArgument("trainingId") {
                        type = NavType.LongType
                    },
                    navArgument("name") {
                        type = NavType.StringType
                    }
                )
            ) { backStackEntry ->
                val trainingId = backStackEntry.arguments?.getLong("trainingId")
                val name = backStackEntry.arguments?.getString("name")
                TrainingBlockHandleExerciseScreen(mainNavController, trainingId!!, name!!)
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
