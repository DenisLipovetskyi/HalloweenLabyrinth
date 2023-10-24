package com.example.halloweenlabyrinth.model

import com.example.halloweenlabyrinth.logic.LabyrinthGameLogic

data class Tile(
    val type: String, // FIXED, MOVABLE
    val treasure: String?, // Represents the treasure on this tile. Null if no treasure.
    val player: LabyrinthGameLogic.Player? // Represents the player on this tile. Null if no player.
)
