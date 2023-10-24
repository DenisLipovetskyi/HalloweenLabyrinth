package com.example.halloweenlabyrinth.model

data class Tile(
    val type: TileType, // FIXED, MOVABLE
    val treasure: Treasure?, // Represents the treasure on this tile. Null if no treasure.
    val player: Player? // Represents the player on this tile. Null if no player.
)
