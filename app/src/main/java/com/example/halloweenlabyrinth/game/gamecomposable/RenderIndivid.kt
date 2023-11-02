package com.example.halloweenlabyrinth.game.gamecomposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times
import com.example.halloweenlabyrinth.R
import com.example.halloweenlabyrinth.game.data.*

private const val TILE_SIZE = 90

@Composable
fun TreasureComposable(treasure: Treasure) {
    Box(
        modifier = Modifier
            .size(TILE_SIZE.dp)
            .background(Color.White)
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_treasure_image),
            contentDescription = "Treasure"
        )
    }
}

@Composable
fun TileComposable(tile: Tile, onTileClick: (Point) -> Unit) {
    Box(
        modifier = Modifier
            .size(TILE_SIZE.dp)
            .background(
                when (tile.type) {
                    TileType.MOVABLE -> Color.Red
                    TileType.FIXED -> Color.Black
                    else -> Color.LightGray
                }
            )
            .clickable { onTileClick(tile.position) }
    )
}

@Composable
fun PlayerComposable(player: Player, onMove: (Direction) -> Unit) {
    Box(
        modifier = Modifier
            .size(TILE_SIZE.dp)
            .background(Color.Blue)
            .offsetFromGrid(player.position)
        // Removing the clickable from the PlayerComposable as the movement logic will be handled by TileComposable
    )
}

fun Modifier.offsetFromGrid(point: Point): Modifier {
    return this.offset(y = point.y * TILE_SIZE.dp, x = point.x * TILE_SIZE.dp)
}



fun Point.offsetBy(direction: Direction): Point {
    return when (direction) {
        Direction.UP -> this.copy(y = this.y - 1)
        Direction.DOWN -> this.copy(y = this.y + 1)
        Direction.LEFT -> this.copy(x = this.x - 1)
        Direction.RIGHT -> this.copy(x = this.x + 1)
    }
}
