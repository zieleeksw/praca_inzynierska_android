package com.example.praca_inzynierska

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.praca_inzynierska.screens.DietConfigurationScreen
import com.example.praca_inzynierska.screens.LoginScreen
import com.example.praca_inzynierska.screens.RegisterScreen

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
        composable("dietconfigurationscreen") {
            DietConfigurationScreen(navController)
        }
    }
}