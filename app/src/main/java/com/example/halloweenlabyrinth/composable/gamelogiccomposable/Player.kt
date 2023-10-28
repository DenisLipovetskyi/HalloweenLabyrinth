package com.example.halloweenlabyrinth.composable.gamelogiccomposable

data class Player(
    var currentPosition: Pair<Int, Int>,
    var startPosition: Pair<Int, Int>,
    var treasureCards: MutableList<Treasure> = mutableListOf()
)
