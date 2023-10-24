package com.example.halloweenlabyrinth.screens


import android.content.Context
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.halloweenlabyrinth.R

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