package com.example.goldenolympus.presentation.mazing

import com.example.goldenolympus.presentation.ui.PoseidonFragment

class Vertex {
    var key: Int
    var edges: Array<Int> = arrayOf()

    // Whether or not the vertex was visited by the MazeBuilder
    var visited: Boolean
    var distance: Int
    var previous: Vertex?
    var color: PoseidonFragment.Color
    val INFINITY = 10000

    constructor(key: Int) {
        this.key = key
        visited = false
        distance = INFINITY
        previous = null
        color = PoseidonFragment.Color.WHITE
    }

    constructor(key: Int, edges: Array<Int>) {
        this.key = key
        this.edges = edges
        visited = false
        distance = INFINITY
        previous = null
        color = PoseidonFragment.Color.WHITE
    }

    @JvmName("setKey1")
    fun setKey(key: Int) {
        this.key = key
    }

    @JvmName("setEdges1")
    fun setEdges(edges: Array<Int>) {
        this.edges = edges
    }

    fun setVisitedToTrue() {
        visited = true
    }

    @JvmName("setDistance1")
    fun setDistance(d: Int) {
        distance = d
    }

    @JvmName("setPrevious1")
    fun setPrevious(v: Vertex?) {
        previous = v
    }

    @JvmName("setColor1")
    fun setColor(color: PoseidonFragment.Color) {
        this.color = color
    }
}
