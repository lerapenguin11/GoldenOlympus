package com.example.goldenolympus.presentation.mazing

import android.app.Activity
import android.content.Context
import android.graphics.*
import android.graphics.drawable.GradientDrawable
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.View
import androidx.core.content.ContextCompat
import com.example.goldenolympus.R
import java.lang.Boolean
import java.util.*
import kotlin.Array
import kotlin.BooleanArray
import kotlin.Int

class MazeView : View {
    var mazePaint: Paint? = null
    var screenWidth = 0
    var screenHeight = 0
    var cellWidth = 0
    var cellHeight = 0
    var padding = 0
    var displaymetrics: DisplayMetrics? = null
    var mazeSize = 3
    lateinit var verticalLines: Array<BooleanArray>
    lateinit var horizontalLines: Array<BooleanArray>
    lateinit var listOfSolutionVertecesKeys: Array<Int>
    var lengthOfSolutionPath = 0
    var graph: Graph? = null
    var fractionOfWallsToRemove = 0.15

    constructor(context: Context?, size: Int) : super(context) {
        mazeSize = size
        graph = Graph(size)
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    private fun init() {
        mazePaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mazePaint!!.shader = LinearGradient(104f, 52f, 0f, 52f, Color.parseColor("#ACF0FF"),
            Color.parseColor("#0073DE"), Shader.TileMode.MIRROR)
        mazePaint!!.style = Paint.Style.STROKE
        mazePaint!!.strokeCap = Paint.Cap.ROUND
        mazePaint!!.strokeJoin = Paint.Join.ROUND
        mazePaint!!.strokeWidth = 14f
        displaymetrics = DisplayMetrics()
        padding = 64
        (context as Activity).windowManager
            .defaultDisplay
            .getMetrics(displaymetrics)
        screenWidth = displaymetrics!!.widthPixels - padding
        screenHeight = displaymetrics!!.heightPixels - padding
        cellWidth = screenWidth / mazeSize
        cellHeight = cellWidth

        verticalLines = Array(mazeSize) { BooleanArray(mazeSize + 1) }
        horizontalLines = Array(mazeSize + 1) {
            BooleanArray(
                mazeSize
            )
        }
        for (i in 0 until mazeSize) {
            Arrays.fill(verticalLines[i], Boolean.TRUE)
        }
        for (j in 0 until mazeSize + 1) {
            Arrays.fill(horizontalLines[j], Boolean.TRUE)
        }
        horizontalLines[mazeSize][0] = false

        val graphStartKey = (graph!!.size - Math.sqrt(graph!!.size.toDouble())).toInt()

        // Declare the first cell of the maze as visited
        graph!!.V[graphStartKey]!!.visited = true
        MazeBuilder.carveWay(graph!!, graph!!.V[graphStartKey]!!, this)

        val rand = Random()
        var holes = 0
        while (holes < Math.floor(Math.pow(fractionOfWallsToRemove * mazeSize, 2.0))) {
            val randomXvertical = rand.nextInt(mazeSize - 1)
            val randomYvertical = rand.nextInt(mazeSize - 1) + 1
            verticalLines[randomXvertical][randomYvertical] = false
            val randomXhorizontal = rand.nextInt(mazeSize - 1) + 1
            val randomYhorizontal = rand.nextInt(mazeSize - 1)
            horizontalLines[randomXhorizontal][randomYhorizontal] = false
            holes++
        }
        MazeBuilder.removeEdges(this)
        listOfSolutionVertecesKeys = LongestPathFinder.findLongestPath(graph!!).filterNotNull().toTypedArray()
        lengthOfSolutionPath = listOfSolutionVertecesKeys.size

        val endKey = listOfSolutionVertecesKeys[0]

        val horizontalEnd =
            if (endKey < mazeSize || endKey >= mazeSize * (mazeSize - 1)) true else false
        if (horizontalEnd) {
            if (endKey < mazeSize) { // top row of horizontal lines
                horizontalLines[0][endKey] = false
            } else { // bottom row of horizontal lines
                horizontalLines[mazeSize][endKey % mazeSize] = false
            }
        } else {
            if (endKey % mazeSize == 0) { // left most column of vertical lines
                verticalLines[endKey / mazeSize][0] = false
            } else { // right most column of vertical lines
                verticalLines[endKey / mazeSize][mazeSize] = false
            }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        // Iterate over the boolean arrays to draw walls
        for (i in 0 until mazeSize + 1) {
            for (j in 0 until mazeSize + 1) {
                val x = (j * cellWidth + padding / 2).toFloat()
                val y = (i * cellHeight + padding / 2).toFloat()
                if (i < mazeSize && verticalLines[i][j]) {
                    // Draw a vertical line
                    canvas.drawLine(
                        x,  //start X
                        y,  //start Y
                        x,  //stop X
                        y + cellHeight,  //stop Y
                        mazePaint!!
                    )
                }
                if (j < mazeSize && horizontalLines[i][j]) {
                    // Draw a horizontal line
                    canvas.drawLine(
                        x,  //startX
                        y,  //startY
                        x + cellWidth,  //stopX
                        y,  //stopY
                        mazePaint!!
                    )
                }
            }
        }
    }
}
