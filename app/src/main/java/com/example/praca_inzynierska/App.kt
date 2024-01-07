package com.example.praca_inzynierska

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.praca_inzynierska.screens.DietConfigurationScreen
import com.example.praca_inzynierska.screens.LoginScreen
import com.example.praca_inzynierska.screens.RegisterScreen

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "loginscreen") {
        composable("loginscreen") {
            LoginScreen(navController)
        }
        composable("registerscreen") {
            RegisterScreen(navController)
        }
        composable(
            "dietconfigurationscreen/{userId}/{token}",
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
            DietConfigurationScreen(navController, userId, token)
        }
    }
}