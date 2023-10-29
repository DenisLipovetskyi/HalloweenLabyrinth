package com.example.halloweenlabyrinth.game.gamecomposable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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

@Composable
fun TileComposable(tile: Tile) {
    val size = 50.dp
    Box(
        modifier = Modifier
            .size(size)
            .background(if (tile.isMovable) Color.Gray else Color.DarkGray)
    )
}

@Composable
fun TreasureComposable(treasure: Treasure) {
    Image(
        painter = painterResource(id = R.drawable.ixxxxxon),
        contentDescription = "Treasure Icon"
    )
    }

