package com.example.halloweenlabyrinth.game.gamecomposable

import com.example.halloweenlabyrinth.game.GameView
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.halloweenlabyrinth.game.GameLogic
import com.example.halloweenlabyrinth.game.data.*

@Composable
fun LabyrinthGame(viewModel: GameView, gameLogic: GameLogic) {  // Add gameLogic here
    val gameState by viewModel.gameState

    Box(modifier = Modifier.fillMaxSize()) {
        // Render Tiles
        gameState.tiles.forEachIndexed { index, tile ->
            TileComposable(tile, index, viewModel.gridSize)
        }

        // Render Players
        gameState.players.forEach { player ->
            PlayerComposable(player) { direction ->
                gameLogic.movePlayer(direction)
            }
        }
        // Render Treasures
        gameState.treasures.forEach { treasure ->
            TreasureComposable(treasure)
        }
    }
}

@Composable
fun BoxWithPosition(index: Int, gridSize: Int, content: @Composable () -> Unit) {
    val tileSize = 50.dp
    Box(
        modifier = Modifier
            .size(tileSize)
            .offset(y = (index / gridSize * tileSize.value).dp, x = (index % gridSize * tileSize.value).dp)
    ) {
        content()
    }
}

@Composable
fun PlayerComposable(player: Player, onMove: (Direction) -> Unit) {
    Text(
        text = "Player",  // Just a placeholder for now
        modifier = Modifier.clickable(onClick = {
            onMove(Direction.UP)  // Example direction, update accordingly
        })
    )
}

fun Modifier.offsetFromGrid(point: Point): Modifier {
    val tileSize = 50.dp
    return this.offset(y = point.y * tileSize.value.dp, x = point.x * tileSize.value.dp)
}

@Composable
fun FeedbackMessage(message: String) {
    Text(text = message)
}
