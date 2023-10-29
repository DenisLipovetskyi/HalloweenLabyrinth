package com.example.halloweenlabyrinth.game

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.halloweenlabyrinth.game.data.GameState
import com.example.halloweenlabyrinth.game.data.Player
import com.example.halloweenlabyrinth.game.data.Point
import com.example.halloweenlabyrinth.game.data.Tile
import com.example.halloweenlabyrinth.game.data.Treasure

class GameView : ViewModel() {
    val gridSize = 7

    val gameState = mutableStateOf(GameState(emptyArray(), emptyList(), emptyList()))

    init {
        val initialTreasures = generateDefaultTreasures()
        val initialPlayers = listOf(Player("Player1", cards = treasureCards(initialTreasures)))

        val boardTiles = Array(gridSize) { Array(gridSize) { Tile() } }

        val pathTiles = List(25) { Tile(type = "P") }
        val wallTiles = List(20) { Tile(type = "W") }
        val movableTiles = List(14) { Tile(type = "M", isMovable = true) }
        val allTiles = (pathTiles + wallTiles + movableTiles).shuffled()

        allTiles.forEachIndexed { index, tile ->
            val row = index / gridSize
            val col = index % gridSize
            boardTiles[row][col] = tile
        }

        gameState.value = GameState(
            tiles = boardTiles,
            players = initialPlayers,
            treasures = initialTreasures
        )
    }

    val currentPlayer: Player
        get() = gameState.value.players.first()

    fun findTreasure(player: Player) {
        val playerPosition = player.position
        gameState.value.treasures.find { it.position == playerPosition }?.let {
            val updatedTreasures = gameState.value.treasures.toMutableList()
            updatedTreasures.remove(it)
            gameState.value = gameState.value.copy(treasures = updatedTreasures)
        }
    }

    private fun generateDefaultTreasures(): List<Treasure> {
        return List(12) {
            Treasure("gold", Point((0 until gridSize).random(), (0 until gridSize).random()))
        }
    }

    private fun treasureCards(treasures: List<Treasure>) = treasures.shuffled().take(gridSize)

    fun resetGame() {
        // Reinitialize your game state
        initializeGameState()  // Call the extracted function here.
    }

    private fun init(function: () -> Unit) {

    }

    private fun initializeGameState() {
        val initialTreasures = generateDefaultTreasures()
        val initialPlayers = listOf(Player("Player1", cards = treasureCards(initialTreasures)))

        val boardTiles = Array(gridSize) { Array(gridSize) { Tile() } }

        val pathTiles = List(25) { Tile(type = "P") }
        val wallTiles = List(20) { Tile(type = "W") }
        val movableTiles = List(14) { Tile(type = "M", isMovable = true) }
        val allTiles = (pathTiles + wallTiles + movableTiles).shuffled()

        allTiles.forEachIndexed { index, tile ->
            val row = index / gridSize
            val col = index % gridSize
            boardTiles[row][col] = tile
        }

        gameState.value = GameState(
            tiles = boardTiles,
            players = initialPlayers,
            treasures = initialTreasures
        )
    }
}

