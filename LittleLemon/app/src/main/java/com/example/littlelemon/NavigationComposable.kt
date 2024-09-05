package com.example.littlelemon

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation

@Composable
fun MyNavigation(navController: NavHostController) {

    val context = LocalContext.current

    val sharedPreferences = context.getSharedPreferences("UserPrefs", Context.MODE_PRIVATE)
    val startDest = if (sharedPreferences.contains("firstName")) { Home } else { Onboarding }

    NavHost(navController = navController, startDestination = startDest.route) {
        composable(Onboarding.route) {
            Onboarding(navController)
        }

        composable(Home.route) {
            Home(navController)
        }

        composable(Profile.route) {
            Profile(navController)
        }
    }
}