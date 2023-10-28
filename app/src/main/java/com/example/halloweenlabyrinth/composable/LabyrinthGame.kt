package com.example.halloweenlabyrinth.composable

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.halloweenlabyrinth.composable.gamelogiccomposable.GameViewModel
import com.google.android.gms.maps.model.Tile
import kotlin.reflect.KProperty

private val Tile.player: Any
    get() {
        TODO("Not yet implemented")
    }

private val Tile.treasure: Any
    get() {
        TODO("Not yet implemented")
    }

@Composable
fun LabyrinthGame(viewModel: GameViewModel) {
    val gameState by viewModel.gameState.observeAsState()
    val currentPlayer by viewModel. currentPlayer.observeAsState()
    val currentTreasure by viewModel.currentTreasure.observeAsState()

    Text(text = "Current Player: ${currentPlayer?.name ?: ""}")
    Text(text = "Current Treasure: ${currentTreasure ?: ""}")

    // Layout to represent the game board
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        gameState?.forEachIndexed { rowIndex, row ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                row.forEachIndexed { colIndex, tile ->
                    TileBox(tile = tile) {
                        onTileClick(rowIndex, colIndex, viewModel)
                    }
                }
            }
        }
    }

    val showInvalidMoveToast by viewModel.showInvalidMoveToast.observeAsState()

    showInvalidMoveToast?.getContentIfNotHandled()?.let {
        Toast.makeText(LocalContext.current, "Invalid move!", Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun TileBox(tile: Tile, onClick: () -> Unit) {
    val backgroundColor = when (tile) {
is Tile -> Color.Gray
        else -> Color.White
    }

    Box(
        modifier = Modifier
            .size(60.dp)
            .background(backgroundColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        tile.player?.let {
            Text(text = "ca", color = Color.Black)
        }
        tile.treasure?.let {
            Text(text = "cdw", color = Color.Red)
        }
    }
}

fun onTileClick(rowIndex: Int, colIndex: Int, viewModel: GameViewModel) {
    val gameLogic = viewModel.gameLogic
    val currentPlayer = gameLogic.getCurrentPlayer()
    val currentPlayerPosition = gameLogic.findPlayerPosition(currentPlayer)

    if (gameLogic.canMove(currentPlayerPosition, Pair(rowIndex, colIndex))) {
        gameLogic.movePlayer(currentPlayer, Pair(rowIndex, colIndex))

        if (gameLogic.hasFoundTreasure(currentPlayer)) {
            val playerPos = gameLogic.findPlayerPosition(currentPlayer)
            // Assuming you have a function in GameViewModel to update a specific tile's treasure
            viewModel.updateTileTreasure(playerPos.first, playerPos.second, null)
        }

        gameLogic.switchTurn()
    } else {
        // Handle invalid move feedback
        viewModel.showInvalidMove()
    }

    viewModel.updateGameState(gameLogic.getBoard())
}

open class Event<out T>(private val content: T) {
    var hasBeenHandled = false
        private set

    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    fun peekContent(): T = content
}
