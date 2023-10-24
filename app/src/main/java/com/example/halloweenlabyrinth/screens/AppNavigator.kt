package com.example.halloweenlabyrinth.screens

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.halloweenlabyrinth.composable.LabyrinthGame
import com.example.halloweenlabyrinth.composable.WebViewScreen


@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val isPolicyAccepted = sharedPreferences.getBoolean("policy_accepted", false)

    NavHost(navController = navController, startDestination = if (isPolicyAccepted) "game_screen_route" else "privacy_policy_route") {
        composable("privacy_policy_route") {
            PrivacyPolicyScreen(navController = navController)
        }
        composable("game_screen_route") {
            GameScreen(navController = navController)
        }
        composable("WebView_route/{link}") { backStackEntry ->
            val link = backStackEntry.arguments?.getString("link") ?: ""
            WebViewScreen(url = link)
        }
        composable("labyrinth_game_route") {
            LabyrinthGame()
        }
    }
}