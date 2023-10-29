package com.example.halloweenlabyrinth.game.gamecomposable

import androidx.compose.foundation.Image
import com.example.halloweenlabyrinth.game.GameView
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import com.example.halloweenlabyrinth.R
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.halloweenlabyrinth.game.data.Direction
import androidx.compose.ui.unit.times
import com.example.halloweenlabyrinth.game.GameLogic
import com.example.halloweenlabyrinth.game.data.*

@Composable
fun LabyrinthGame(viewModel: GameView, gameLogic: GameLogic) {
    val gameState by viewModel.gameState

    Box(modifier = Modifier.fillMaxSize()) {
        // Render Tiles
        Column {
            for (row in gameState.tiles) {
                Row {
                    for (tile in row) {
                        TileComposable(tile)
                    }
                }
            }
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
    Image(
        painter = painterResource(id = R.drawable.icon),
        contentDescription = "Player Icon"
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