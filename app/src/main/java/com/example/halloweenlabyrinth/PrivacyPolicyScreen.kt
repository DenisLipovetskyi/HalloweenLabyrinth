package com.example.halloweenlabyrinth


import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun PrivacyPolicyScreen(onDecisionMade: (Boolean) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        item {

        }

        item {
            Spacer(modifier = Modifier.height(16.dp)) // Provides space between items.
        }

        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { onDecisionMade(true) }) {
                    Text(text = "Accept")
                }

                Button(onClick = { onDecisionMade(false) }) {
                    Text(text = "Deny")
                }
            }
        }
    }
}

@Composable
fun MyApp(context: Context, navController: NavHostController) {
    val decisionMade: Boolean by remember { mutableStateOf(hasRespondedToPrivacyPolicy(context)) }


    NavHost(navController = navController, startDestination = if (decisionMade) "game" else "privacy") {
        composable("privacy") {
            PrivacyPolicyScreen { accepted ->
                saveUserDecision(context, accepted)
                navController.navigate("game") {
                    popUpTo(navController.graph.startDestinationId)
                    launchSingleTop = true
                }
            }
        }

        composable("game") {
            // Your GameScreen Composable here
            GameScreenContent()
        }
    }
}

@Composable
fun GameScreenContent() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Game Screen")
    }
}




fun saveUserDecision(context: Context, accepted: Boolean) {
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    sharedPreferences.edit().putBoolean("hasAcceptedPrivacyPolicy", accepted).apply()
}

fun hasRespondedToPrivacyPolicy(context: Context): Boolean {
    val sharedPreferences = context.getSharedPreferences("AppPreferences", Context.MODE_PRIVATE)
    return sharedPreferences.contains("hasAcceptedPrivacyPolicy")
}

@Composable
fun GameScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Game Screen")
    }
}
