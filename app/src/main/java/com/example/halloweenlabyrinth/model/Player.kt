package com.example.halloweenlabyrinth.model

data class Player(
    val id: Int,
    val startPosition: Pair<Int, Int>, // x,y coordinates of start position
    var currentPosition: Pair<Int, Int>,
    val treasureCards: MutableList<Treasure>
)
