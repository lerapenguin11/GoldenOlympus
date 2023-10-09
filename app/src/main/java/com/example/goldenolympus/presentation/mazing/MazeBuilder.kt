package com.example.goldenolympus.presentation.mazing

import com.example.goldenolympus.presentation.ui.PoseidonFragment

object MazeBuilder {
    fun carveWay(graph: Graph, v: Vertex, mazeView: MazeView) {
        val n = Math.sqrt(graph.size.toDouble()).toInt()
        for (i in 0 until v.edges.size) {

            // If the current neighbor was not visited yet,
            // break the wall between the current vertex and the neighbor
            // and then recursively carve way from the neighbor to its neighbors
            if (v.edges.get(i) != null && !graph.V[v.edges.get(i)]!!.visited) {
                // Find which is the larger-key vertex
                var biggerVertex: Vertex
                var smallerVertex: Vertex
                if (v.key >= graph.V[v.edges.get(i)]!!.key) {
                    biggerVertex = v
                    smallerVertex = graph.V[v.edges.get(i)]!!
                } else {
                    biggerVertex = graph.V[v.edges.get(i)]!!
                    smallerVertex = v
                }
                val row: Int = biggerVertex.key / n
                val column : Int = biggerVertex.key % n

                if (biggerVertex.key / n === smallerVertex.key / n) {
                    // vertical wall
                    mazeView.verticalLines[row][column] = false
                } else {
                    // Horizontal wall
                    mazeView.horizontalLines[row][column] = false
                }

                // Change visited to true
                graph.V[v.edges.get(i)]!!.setVisitedToTrue()

                // Move on recursively
                carveWay(graph, graph.V[v.edges.get(i)]!!, mazeView)
            }
        }
        // All neighbors were visited, move back in recursion
    }

    fun removeEdges(mazeView: MazeView) {
        // Iterate over the verticalLines
        var counter = 0
        for (linesRow in 0 until mazeView.verticalLines.size) {

            // Don't iterate over the first and last vertical lines as they are the maze's borders
            for (linesColumn in 1 until mazeView.verticalLines.get(0).size - 1) {
                // If there is no line, remove the edge
                if (mazeView.verticalLines.get(linesRow).get(linesColumn) === false) {
                    val currentEdges: Array<Int> = mazeView.graph!!.V.get(counter)!!.edges
                    val currentEdgesNextVertex: Array<Int> = mazeView.graph!!.V.get(counter + 1)!!.edges

                    val poseidonFragment = PoseidonFragment()
                    // Remove the relevant edge from current edges arrays
                    val currentVertexKey: Int = linesRow * mazeView.mazeSize + (linesColumn - 1)
                    val neighborVertexKey = currentVertexKey + 1
                    val newEdgesV: Array<Int?> = poseidonFragment.removeValueFromArray(currentEdges, neighborVertexKey)
                    val newEdgesNeighbor: Array<Int?> = poseidonFragment.removeValueFromArray(currentEdgesNextVertex, currentVertexKey)
                    mazeView.graph!!.V.get(counter)!!.edges = newEdgesV.filterNotNull().toTypedArray()
                    mazeView.graph!!.V.get(counter + 1)!!.edges = newEdgesNeighbor.filterNotNull().toTypedArray()
                }
                counter++
            }
            counter++
        }

        // Restart counter to zero
        // Iterate over the horizontalLines
        counter = 0

        // Don't iterate over the first and last horizontal lines as they are the maze's borders
        for (linesRow in 1 until mazeView.horizontalLines.size - 1) {
            for (linesColumn in 0 until mazeView.horizontalLines.get(0).size) {

                // If there is not line, remove the edge
                if (mazeView.horizontalLines.get(linesRow).get(linesColumn) === false) {
                    val currentEdges: Array<Int> = mazeView.graph!!.V.get(counter)!!.edges
                    val currentEdgesNextVertex: Array<Int> =
                        mazeView.graph!!.V.get(counter + mazeView.mazeSize)!!.edges
                    val poseidonFragment = PoseidonFragment()
                    // Remove the relevant edge from current edges arrays
                    val currentVertexKey: Int = (linesRow - 1) * mazeView.mazeSize + linesColumn
                    val neighborVertexKey: Int = currentVertexKey + mazeView.mazeSize
                    val newEdgesV: Array<Int?> =
                        poseidonFragment.removeValueFromArray(currentEdges, neighborVertexKey)!!

                    val newEdgesNeighbor: Array<Int?>? = poseidonFragment.removeValueFromArray(currentEdgesNextVertex, currentVertexKey)
                    mazeView.graph!!.V.get(counter)!!.edges = newEdgesV.filterNotNull().toTypedArray()
                    mazeView.graph!!.V.get(counter + mazeView.mazeSize)!!.edges = newEdgesNeighbor!!.filterNotNull().toTypedArray()
                }
                counter++
            }
        }
    }
}
