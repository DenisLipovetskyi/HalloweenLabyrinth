package com.example.halloweenlabyrinth.game.gamecomposable

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.halloweenlabyrinth.game.data.*

class GameViewModel(private val screenWidthDp: Int, private val screenHeightDp: Int) : ViewModel() {

    companion object {
        private const val GRID_SIZE = 7
        private const val TOTAL_TREASURES = 12
        private const val NUMBER_OF_TILES_IN_ROW = GRID_SIZE  // Assuming GRID_SIZE is the number of tiles in a row
    }

    val hasWon: Boolean
        get() = gameState.value.treasures.isEmpty()

    val isGameOver: Boolean
        get() = gameState.value.players.isEmpty() || hasWon


    val tileSize: Float
        get() = (screenWidthDp / NUMBER_OF_TILES_IN_ROW).toFloat()

    val gameState = mutableStateOf(GameState(emptyArray(), mutableListOf(), mutableListOf()))

    init {
        initializeGameState()
    }

    private fun initializeGameState() {
        gameState.value = GameState(
            tiles = generateDefaultTiles(),
            players = generateDefaultPlayers(),
            treasures = generateUniqueTreasures()
        )
    }

    private fun generateDefaultTiles(): Array<Array<Tile>> = Array(GRID_SIZE) { y ->
        Array(GRID_SIZE) { x ->
            when {
                x == 0 || x == GRID_SIZE - 1 || y == 0 || y == GRID_SIZE - 1 -> Tile(TileType.FIXED, Point(x, y))
                else -> Tile(TileType.MOVABLE, Point(x, y))
            }
        }
    }

    private fun generateDefaultPlayers(): MutableList<Player> = mutableListOf(
        Player("Player1", Point(1, 1), mutableListOf()),
        Player("Player2", Point(GRID_SIZE - 2, GRID_SIZE - 2), mutableListOf())
    )

    private fun generateUniqueTreasures(): MutableList<Treasure> {
        val treasureLocations = mutableSetOf<Point>()
        while (treasureLocations.size < TOTAL_TREASURES) {
            treasureLocations.add(Point((1 until GRID_SIZE - 1).random(), (1 until GRID_SIZE - 1).random()))
        }
        return treasureLocations.map { Treasure("gold", it) }.toMutableList()
    }

    fun resetGame() {
        initializeGameState()
    }
}
