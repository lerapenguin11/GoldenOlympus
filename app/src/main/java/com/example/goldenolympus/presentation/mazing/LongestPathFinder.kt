package com.example.goldenolympus.presentation.mazing

import com.example.goldenolympus.presentation.ui.PoseidonFragment
import java.util.*

object LongestPathFinder {
    fun findLongestPath(graph: Graph): Array<Int?> {
        val transposedGraph = graph.negateGraph()
        val resultArray = ArrayList<Int>()
        val n = Math.sqrt(transposedGraph.size.toDouble())
        val startIndex = (transposedGraph.size - n).toInt()
        val start =
            transposedGraph.V[startIndex]!! // To start from the top left corner, choose index 0
        var maxDistance = 0
        var isOuter: Boolean
        var isStart: Boolean
        var farthestVertex = start
        start.setColor(PoseidonFragment.Color.GRAY)
        start.setDistance(0)
        // Create an empty queue Q
        val Q: Queue<Vertex> = LinkedList()
        // insert start to Q
        Q.add(start)
        while (!Q.isEmpty()) {
            val u = Q.remove()
            for (i in 0 until u.edges.size) {

                // First make sure the adjacency list is not empty
                if (u.edges.size > 0) {
                    if (transposedGraph.V[u.edges.get(i)]!!.color === PoseidonFragment.Color.WHITE) {
                        transposedGraph.V[u.edges.get(i)]!!.setColor(PoseidonFragment.Color.GRAY)
                        transposedGraph.V[u.edges.get(i)]!!.setDistance(u.distance + 1)
                        isOuter = (u.edges.get(i) + 1) % n < 2 || u.edges.get(i) < n || u.edges.get(i) > n * (n - 1) - 1
                        isStart = transposedGraph.V[u.edges.get(i)]!!.equals(start)
                        if (transposedGraph.V[u.edges.get(i)]!!.distance > maxDistance && isOuter && !isStart) {
                            maxDistance = u.distance + 1
                            farthestVertex = transposedGraph.V[u.edges.get(i)]!!
                        }
                        transposedGraph.V[u.edges.get(i)]!!.setPrevious(u)
                        Q.add(transposedGraph.V[u.edges.get(i)])
                    }
                }
            }
            u.setColor(PoseidonFragment.Color.BLACK)
        }

        // Retrieve a list of verteces keys
        var currVertex = farthestVertex
        resultArray.add(currVertex.key)
        while (currVertex.previous != null) {
            currVertex = currVertex.previous!!
            resultArray.add(currVertex.key)
        }
        val result = arrayOfNulls<Int>(resultArray.size)
        resultArray.toArray(result)
        return result
    }
}
