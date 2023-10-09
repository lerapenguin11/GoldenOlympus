package com.example.goldenolympus.presentation.mazing

import com.example.goldenolympus.presentation.ui.PoseidonFragment
import java.util.*

class Graph(n: Int) {
    var size: Int
    var V: Array<Vertex?> = arrayOf()

    init {
        size = n * n
        V = arrayOfNulls(size)
        var counter = 0
        for (r in 0 until n) {
            for (c in 0 until n) {
                V[counter] = Vertex(counter)
                val top = if (r == 0) false else true
                val right = if (c == n - 1) false else true
                val bottom = if (r == n - 1) false else true
                val left = if (c == 0) false else true
                val edgesArrayList = ArrayList<Int?>()
                if (top) {
                    edgesArrayList.add(n * (r - 1) + c) // n * r + c
                }
                if (right) {
                    edgesArrayList.add(n * r + (c + 1))
                }
                if (bottom) {
                    edgesArrayList.add(n * (r + 1) + c)
                }
                if (left) {
                    edgesArrayList.add(n * r + (c - 1))
                }
                val currentEdgesNextVertex: Array<Int?> = arrayOfNulls<Int>(edgesArrayList.size)
                    V[counter]!!.edges = currentEdgesNextVertex.filterNotNull().toTypedArray()
                Collections.shuffle(edgesArrayList)
                V[counter]!!.edges = edgesArrayList.toArray(V[counter]!!.edges)
                counter++
            }
        }
    }

    fun negateGraph(): Graph {
        val n = Math.sqrt(size.toDouble()).toInt()
        val result = Graph(n) // At this point the result graph has all possible edges

        // Iterate over all vertices and negate the adjacency lists
        for (i in 0 until result.size) {
            val oldEdges: Array<Int> = V[i]!!.edges!!
            var newAdjacencyList: Array<Int> = result.V[i]!!.edges!!
            val edgesToRemove = ArrayList<Int>()
            // Iterate over the current vertex adjacency list
            for (e in newAdjacencyList.indices) {
                // If the original graph contains the current edge, delete it from the new graph
                if (Arrays.asList(*oldEdges).contains(newAdjacencyList[e])) {
                    edgesToRemove.add(newAdjacencyList[e])
                }
            }
            // Remove the edges to remove from the new adjacency list
            for (newE in edgesToRemove.indices) {
                val poseidonFragment = PoseidonFragment()
                val currentEdgesNextVertex: Array<Int?> = poseidonFragment.removeValueFromArray(
                    newAdjacencyList,
                    edgesToRemove[newE])
                newAdjacencyList = currentEdgesNextVertex.filterNotNull().toTypedArray()

            }
            result.V[i]!!.edges = newAdjacencyList
        }
        return result
    }
}
