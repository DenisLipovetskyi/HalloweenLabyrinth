package com.example.halloweenlabyrinth.logic

import android.graphics.Path
import androidx.compose.runtime.MutableState
import com.example.halloweenlabyrinth.model.Direction
import com.example.halloweenlabyrinth.model.Player
import kotlin.math.max
import kotlin.math.min


class LabyrinthGameLogic  {
    fun canMove(player: Player, direction: Path.Direction): Boolean {
        val newPos = player.position.copy()
        when (direction) {
            Direction.UP -> newPos.y -= 1
            Direction.DOWN -> newPos.y += 1
            Direction.LEFT -> newPos.x -= 1
            Direction.RIGHT -> newPos.x += 1
            else -> {}
        }

        // Check maze boundaries
        if (newPos.x !in 0..6 || newPos.y !in 0..6) return false

        // Check if the next tile is a road
        return board[newPos.y][newPos.x].content == TileContent.ROAD
    }

    fun movePlayer(player: Player, direction: Direction) {
        if (!canMove(player, direction)) return

        // Update player position
        when (direction) {
            Direction.UP -> {
                player.position.y -= 1
                shiftColumn(player.position.x, false)
            }
            Direction.DOWN -> {
                player.position.y += 1
                shiftColumn(player.position.x, true)
            }
            Direction.LEFT -> {
                player.position.x -= 1
                shiftRow(player.position.y, false)
            }
            Direction.RIGHT -> {
                player.position.x += 1
                shiftRow(player.position.y, true)
            }
        }
    }

    // Shifts a column up or down based on the down flag
    fun shiftColumn(column: Int, down: Boolean) {
        if (down) {
            val lastTile = board[6][column]
            for (i in 6 downTo 1) {
                board[i][column] = board[i - 1][column]
            }
            board[0][column] = lastTile
        } else {
            val firstTile = board[0][column]
            for (i in 0 until 6) {
                board[i][column] = board[i + 1][column]
            }
            board[6][column] = firstTile
        }
    }

    // Shifts a row left or right based on the right flag
    fun shiftRow(row: Int, right: Boolean) {
        if (right) {
            val lastTile = board[row][6]
            for (i in 6 downTo 1) {
                board[row][i] = board[row][i - 1]
            }
            board[row][0] = lastTile
        } else {
            val firstTile = board[row][0]
            for (i in 0 until 6) {
                board[row][i] = board[row][i + 1]
            }
            board[row][6] = firstTile
        }
    }
//    data class Tile(val type: String, var treasure: String?, var player: Player?)
//    data class Player(val id: Int, val name: String)
//
//    var extraTile: Tile = Tile("path", null, null)
//    var currentPlayerIndex: Int = 0
//    var lastPushedOutPosition: Pair<Int, Int>? = null
//    var currentTreasures: MutableList<String> = mutableListOf("Gold", "Silver")
//
//    private var board: Array<Array<Tile>> = arrayOf()
//    private val players = mutableListOf<Player>()
//
//    init {
//        setupGame()
//    }
//
//    private fun setupGame() {
//        // Initializing a 5x5 board as an example
//        board = Array(5) { Array(5) { Tile("path", null, null) } }
//
//        // Placing treasures
//        board[1][2].treasure = "Gold"
//        board[3][3].treasure = "Silver"
//
//        // Initializing players
//        players.add(Player(1, "Player 1"))
//        players.add(Player(2, "Player 2"))
//
//        // Placing players on the board
//        board[0][0].player = players[0]
//        board[4][4].player = players[1]
//    }
//
//    fun getBoard(): Array<Array<Tile>> = board
//    fun getPlayers(): List<Player> = players
//
//    companion object {
//        private var instance: LabyrinthGameLogic? = null
//
//        fun getInstance(): LabyrinthGameLogic {
//            if (instance == null) {
//                instance = LabyrinthGameLogic()
//            }
//            return instance!!
//        }
//    }
//    enum class Direction {
//        TOP, BOTTOM, LEFT, RIGHT
//    }
//
//    fun insertTile(direction: Direction, position: Int) {
//        when(direction) {
//            Direction.TOP -> {
//                for (i in 4 downTo 1) {
//                    board[i][position] = board[i-1][position]
//                }
//                board[0][position] = extraTile
//            }
//            Direction.BOTTOM -> {
//                for (i in 0 until 4) {
//                    board[4-i][position] = board[3-i][position]
//                }
//                board[0][position] = extraTile
//            }
//            Direction.LEFT -> {
//                for (i in 4 downTo 1) {
//                    board[position][i] = board[position][i-1]
//                }
//                board[position][0] = extraTile
//            }
//            Direction.RIGHT -> {
//                for (i in 0 until 4) {
//                    board[position][4-i] = board[position][3-i]
//                }
//                board[position][0] = extraTile
//            }
//        }
//        // Remember to update the `lastPushedOutPosition`
//    }
//
//    fun canMove(from: Pair<Int, Int>, to: Pair<Int, Int>): Boolean {
//        if (from.first == to.first) {
//            for (i in min(from.second, to.second)..max(from.second, to.second)) {
//                if (board[from.first][i].type != "path") return false
//            }
//            return true
//        } else if (from.second == to.second) {
//            for (i in min(from.first, to.first)..max(from.first, to.first)) {
//                if (board[i][from.second].type != "path") return false
//            }
//            return true
//        }
//        return false
//    }
//
//    fun movePlayer(player: Player, to: Pair<Int, Int>) {
//        val currentPosition = findPlayerPosition(player)
//        if (canMove(currentPosition, to)) {
//            board[currentPosition.first][currentPosition.second].player = null
//            board[to.first][to.second].player = player
//        }
//    }
//
//    fun findPlayerPosition(player: Player): Pair<Int, Int> {
//        for (i in board.indices) {
//            for (j in board[i].indices) {
//                if (board[i][j].player == player) {
//                    return Pair(i, j)
//                }
//            }
//        }
//        throw IllegalArgumentException("Player not found on board")
//    }
//    fun hasFoundTreasure(player: Player): Boolean {
//        val position = findPlayerPosition(player)
//        return board[position.first][position.second].treasure == getCurrentTreasure(player)
//    }
//
//    fun switchTurn() {
//        currentPlayerIndex = (currentPlayerIndex + 1) % players.size
//    }
//
//    fun checkWinningCondition(gameLogic: LabyrinthGameLogic): Boolean {
//        // Example: Check if a player has collected all treasures and returned to their starting position.
//        val currentPlayer = gameLogic.getCurrentPlayer()
//        val playerPos = gameLogic.findPlayerPosition(currentPlayer)
//        return playerPos == Pair(0, 0) || playerPos == Pair(4, 4) // TODO: Expand this to match the actual starting positions and treasure conditions.
//    }
//
//    fun resetGame(gameLogic: LabyrinthGameLogic, gameState: MutableState<Array<Array<Tile>>>) {
//        // For now, recreate the game instance
//        LabyrinthGameLogic.instance = LabyrinthGameLogic()
//        gameState.value = gameLogic.getBoard()
//    }
//
//    fun setTreasureAtPosition(position: Pair<Int, Int>, treasure: String?) {
//        board[position.first][position.second].treasure = treasure
//    }
//
//    fun getCurrentTreasure(player: Player): String? {
//        // For simplicity, return the first treasure in the list
//        return currentTreasures.firstOrNull()
//    }
//
//    fun getCurrentPlayer(): Player {
//        return players[currentPlayerIndex]
//    }

}
