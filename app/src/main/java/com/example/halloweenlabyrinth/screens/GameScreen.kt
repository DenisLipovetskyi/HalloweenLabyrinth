package com.example.halloweenlabyrinth.screens

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.halloweenlabyrinth.R
import com.example.halloweenlabyrinth.utils.fetchServerResponse

@Composable
fun GameScreen(navController: NavController) {
    val context = LocalContext.current
    val sharedPreferences = context.getSharedPreferences("user_prefs", Context.MODE_PRIVATE)
    val isPolicyAccepted = sharedPreferences.getBoolean("policy_accepted", false)


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