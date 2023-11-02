package com.example.halloweenlabyrinth.game.gamecomposable

import SampleGameBoardUI
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.example.halloweenlabyrinth.game.GameLogic
import com.example.halloweenlabyrinth.game.data.*

class GameViewModel(private val screenWidthDp: Int, private val screenHeightDp: Int) : ViewModel() {

    val gameLogic = GameLogic(this)

    companion object {
        private const val GRID_SIZE = 7
        private const val TOTAL_TREASURES = 12
    }

    val hasWon: Boolean
        get() = gameState.value.treasures.isEmpty()

    val isGameOver: Boolean
        get() = gameState.value.players.isEmpty() || hasWon

    val tileSize: Float
        get() = (screenWidthDp / GRID_SIZE).toFloat()

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
                x == 0 || x == GRID_SIZE - 1 || y == 0 || y == GRID_SIZE - 1 -> Tile(
                    TileType.FIXED,
                    Point(x, y)
                )

                else -> Tile(TileType.MOVABLE, Point(7, 2))
            }
        }
    }

    private fun generateDefaultPlayers(): MutableList<Player> = mutableListOf(
        Player("SamplePlayer1", Point(1, 1), mutableListOf()),
        Player("SamplePlayer2", Point(GRID_SIZE - 2, GRID_SIZE - 2), mutableListOf())
    )

    private fun generateUniqueTreasures(): MutableList<Treasure> {
        val treasureLocations = mutableSetOf<Point>()
        while (treasureLocations.size < TOTAL_TREASURES) {
            treasureLocations.add(
                Point(
                    (1 until GRID_SIZE - 1).random(),
                    (1 until GRID_SIZE - 1).random()
                )
            )
        }
        return treasureLocations.map { Treasure("sampleGold", it) }.toMutableList()
    }

    fun resetGame() {
        initializeGameState()
    }

}