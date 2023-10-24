package com.example.halloweenlabyrinth


import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.halloweenlabyrinth.composable.WebViewScreen

@Composable
fun PrivacyPolicyScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)

    val policyText = stringResource(id = R.string.privacy_policy)

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = policyText,
            modifier = Modifier.weight(1f).verticalScroll(rememberScrollState())
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = {
                with(sharedPreferences.edit()) {
                    putBoolean("policy_accepted", true)
                    apply()
                }
                navController.navigate("game_screen_route")
            }) {
                Text(text = "Accept")
            }

            Button(onClick = {
                with(sharedPreferences.edit()) {
                    putBoolean("policy_accepted", false) // Reset the accept flag
                    apply()
                }
                navController.navigate("game_screen_route")
            }) {
                Text(text = "Deny")
            }
        }
    }
}


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
    }

    NavHost(navController = navController, startDestination = /* ... */ ) {
        composable("privacy_policy_route") {
            PrivacyPolicyScreen(navController = navController)
        }
        composable("game_screen_route") {
            GameScreen(navController = navController)
        }
        composable("webview_route/{link}") { backStackEntry ->
            val link = backStackEntry.arguments?.getString("link") ?: ""
            WebViewScreen(url = link)
        }
        composable("labyrinth_game_route") {
            // Here, launch your Labyrinth game from the `com.example.halloweenlabyrinth` package
            // Example:
            // LabyrinthGame()   // Assuming you have a composable named LabyrinthGame in your game package.
        }
    }
}

@Composable
fun GameScreen(navController: NavController) {



    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.game_start_screen),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Start Button in the center
        Button(
            onClick = {
                if (isPolicyAccepted) {
                    // Fetch the server response
                    val serverResponse = fetchServerResponse()  // Placeholder. Replace with actual API call.
                    if (serverResponse.contains("http")) {  // A simple check. Refine based on actual response structure.
                        navController.navigate("webview_route/${serverResponse}")
                    } else {
                        navController.navigate("labyrinth_game_route")
                    }
                } else {
                    navController.navigate("labyrinth_game_route")
                }
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(50.dp)
        ) {
            Text(text = "Start Game")
        }

        // Button to Read Privacy Policy Again at the bottom
        IconButton(
            onClick = {
                navController.navigate("privacy_policy_route")
            },
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {
            Icon(imageVector = Icons.Default.Info, contentDescription = "Read Privacy Policy") // Use the appropriate icon
        }
    }
}