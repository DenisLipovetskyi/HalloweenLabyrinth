package com.example.halloweenlabyrinth.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.halloweenlabyrinth.logic.LabyrinthGameLogic

@Composable
fun LabyrinthGame() {
    val gameLogic = remember { LabyrinthGameLogic() } // Initialize game logic

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Use the gameLogic to fetch the game state and render it
        Text(text = "Welcome to the Labyrinth Game!")

        // For now, just a basic demonstration:
        for (row in gameLogic.board) {
            Row {
                for (tile in row) {
                    // Display each tile. You can use simple Box composable for now.
                    Box(modifier = Modifier.size(40.dp).border(1.dp, Color.Black)) {
                        // Here you'll add more UI details based on the tile's attributes
                    }
                }
            }
        }
    }
}