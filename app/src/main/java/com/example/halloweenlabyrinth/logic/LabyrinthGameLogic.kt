package com.example.halloweenlabyrinth.logic
class LabyrinthGameLogic private constructor() {
    data class Tile(val type: String, var treasure: String?, var player: Player?)
    data class Player(val id: Int, val name: String)

    private var board: Array<Array<Tile>> = arrayOf()
    private val players = mutableListOf<Player>()

    init {
        setupGame()
    }

    private fun setupGame() {
        // Initializing a 5x5 board as an example
        board = Array(5) { Array(5) { Tile("path", null, null) } }

        // Placing treasures
        board[1][2].treasure = "Gold"
        board[3][3].treasure = "Silver"

        // Initializing players
        players.add(Player(1, "Player 1"))
        players.add(Player(2, "Player 2"))

        // Placing players on the board
        board[0][0].player = players[0]
        board[4][4].player = players[1]
    }

    fun getBoard(): Array<Array<Tile>> = board
    fun getPlayers(): List<Player> = players

    companion object {
        private var instance: LabyrinthGameLogic? = null

        fun getInstance(): LabyrinthGameLogic {
            if (instance == null) {
                instance = LabyrinthGameLogic()
            }
            return instance!!
        }
    }
}