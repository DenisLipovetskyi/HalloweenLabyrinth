package com.example.halloweenlabyrinth.game.gamecomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.halloweenlabyrinth.game.GameView
import com.example.halloweenlabyrinth.game.data.*

@Composable
fun LabyrinthGame(viewModel: GameView) {
    val gameState by viewModel.gameState

    Box(modifier = Modifier.fillMaxSize()) {
        gameState.tiles.forEachIndexed { index, tile ->
            TileComposable(tile, index, viewModel.gridSize)
        }

        gameState.players.forEach { player ->
            PlayerComposable(player)
        }

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

fun Modifier.offsetFromGrid(point: Point): Modifier {
    val tileSize = 50.dp
    return this.offset(y = point.y * tileSize.value.dp, x = point.x * tileSize.value.dp)
}