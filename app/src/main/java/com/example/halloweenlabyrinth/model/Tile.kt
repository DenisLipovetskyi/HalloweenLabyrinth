package com.example.halloweenlabyrinth.model

import com.example.halloweenlabyrinth.composable.gamelogiccomposable.Player
import com.example.halloweenlabyrinth.logic.TileContent

class Tile {
    internal enum class Type {
        FIXED, MOVABLE
    }

    lateinit var content: TileContent
    private val tileType: Type? = null
    var treasure: Treasure? = null
    private val player: Player? = null // Getters and setters

    // Constructor
}