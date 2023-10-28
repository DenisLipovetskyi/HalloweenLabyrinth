package com.example.halloweenlabyrinth.game.gamecomposable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.halloweenlabyrinth.game.data.*

@Composable
fun TileComposable(tile: Tile, index: Int, gridSize: Int) {
    val size = 50.dp
    Box(
        modifier = Modifier
            .size(size)
            .background(if (tile.isMovable) Color.Gray else Color.DarkGray)
            .offset(y = (index / gridSize * size.value).dp, x = (index % gridSize * size.value).dp)
    )
}

@Composable
fun PlayerComposable(player: Player) {
    val size = 50.dp
    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Blue)
            .offset(y = player.position.y * size.value.dp, x = player.position.x * size.value.dp),
    ) {
        // You can add a Text composable here to display player's name if needed
    }
}

@Composable
fun TreasureComposable(treasure: Treasure) {
    val size = 50.dp
    Box(
        modifier = Modifier
            .size(size)
            .background(Color.Yellow)
            .offset(y = treasure.position.y * size.value.dp, x = treasure.position.x * size.value.dp),
    ) {
        // You can add a Text composable here to display the treasure's type if needed
    }
}
