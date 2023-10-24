package com.example.halloweenlabyrinth.composable.gamelogiccomposable

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.halloweenlabyrinth.logic.LabyrinthGameLogic
import com.example.halloweenlabyrinth.model.TileType

@Composable
fun GameTile(row: Int, col: Int, tile: LabyrinthGameLogic.Tile, onClick: (Int, Int) -> Unit) {
    Box(
        modifier = Modifier
            .size(40.dp)
            .border(1.dp, Color.Gray)
            .clickable { onClick(row, col) }
    ) {
        when {
            tile.player != null -> Text("P")
            tile.treasure != null -> Text("T")
            tile.type == TileType.FIXED.name -> Text("F")
            else -> {}
        }
    }
}