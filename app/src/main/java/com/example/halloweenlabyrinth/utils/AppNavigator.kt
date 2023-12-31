package com.example.halloweenlabyrinth.utils

import com.example.halloweenlabyrinth.game.GameLogic
import com.example.halloweenlabyrinth.game.gamecomposable.GameViewModel
import LabyrinthGame

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.*
import com.example.halloweenlabyrinth.composable.WebViewScreen

import com.example.halloweenlabyrinth.screens.GameScreen
import com.example.halloweenlabyrinth.screens.PrivacyPolicyScreen


@Composable
fun AppNavigator() {
    val navController = rememberNavController()
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val isPolicyAccepted = sharedPreferences.getBoolean("policy_accepted", false)

    // Get screen dimensions
    val screenWidthDp = LocalConfiguration.current.screenWidthDp
    val screenHeightDp = LocalConfiguration.current.screenHeightDp

    // Instantiate GameView and GameLogic here with screen dimensions
    val gameViewModel = remember { GameViewModel(screenWidthDp, screenHeightDp) }
    val gameLogic = remember { GameLogic(gameViewModel) }

    NavHost(navController = navController, startDestination = if (isPolicyAccepted) "game_screen_route" else "privacy_policy_route") {
        composable("privacy_policy_route") { PrivacyPolicyScreen(navController = navController) }
        composable("game_screen_route") { GameScreen(navController = navController) }
        composable("WebView_route/{link}") { backStackEntry ->
            WebViewScreen(url = backStackEntry.arguments?.getString("link") ?: "")
        }
        composable("labyrinth_game_route") {
            LabyrinthGame(viewModel = gameViewModel, gameLogic = gameLogic)
        }
    }
}
