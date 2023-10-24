package com.example.halloweenlabyrinth.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.halloweenlabyrinth.composable.gamelogiccomposable.GameTile
import com.example.halloweenlabyrinth.composable.gamelogiccomposable.GameViewModel
import com.example.halloweenlabyrinth.logic.LabyrinthGameLogic
import androidx.compose.foundation.layout.*


@Composable
fun LabyrinthGame() {
    val gameLogic = remember { LabyrinthGameLogic.getInstance() }  // Use getInstance to get the singleton object
    val gameState = remember { mutableStateOf(gameLogic.getBoard()) }

    // Layout to represent the game board
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        gameState.value.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEachIndexed { colIndex, tile ->
                    GameTile(rowIndex, colIndex, tile) { r, c ->
                        // Handle the tile click here
                        // For instance, if a player moves to this tile, update its state and refresh the UI
                        onTileClick(r, c, gameState, gameLogic)
                    }
                }
            }
        }
    }
}

fun onTileClick(row: Int, col: Int, gameState: MutableState<Array<Array<LabyrinthGameLogic.Tile>>>, gameLogic: LabyrinthGameLogic) {
    // Implement your game logic here when a tile is clicked
    // For instance, move a player to the new tile, collect treasure, etc.

    // After making changes to the game state, refresh the UI by updating the gameState
    gameState.value = gameLogic.getBoard()
}


