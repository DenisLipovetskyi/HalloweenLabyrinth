package com.example.halloweenlabyrinth.logic

import com.example.halloweenlabyrinth.model.Player
import com.example.halloweenlabyrinth.model.Tile
import com.example.halloweenlabyrinth.model.TileType

class LabyrinthGameLogic {

    val board: Array<Array<Tile>> = Array(7) { Array(7) { Tile(TileType.MOVABLE, null, null) } }
    // Initialize board with movable tiles without treasures and players.
    // Later, you'll update it with fixed tiles and starting player positions.

    // List of players. Initialize based on the number of players in the game.
    var players: List<Player> = listOf()

    // ... rest of the game logic
}