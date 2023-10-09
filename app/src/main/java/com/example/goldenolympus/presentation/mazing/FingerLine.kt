package com.example.goldenolympus.presentation.mazing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.example.goldenolympus.R
import com.example.goldenolympus.presentation.ui.PoseidonFragment

class FingerLine : View {
    private var mPaint: Paint? = null
    private var mPath: Path? = null
    private lateinit var solutionPath: Array<IntArray>
    var solutionCellsVisited = 0
    private var solved: ArrayList<Boolean>? = null
    private var solvedMaze = false

    constructor(context: Context?) : this(context, null) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, solutionPath: Array<IntArray>) : super(
        context,
        attrs
    ) {
        this.solutionPath = solutionPath
        init()
    }

    private fun init() {
        // Create the paint brush
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint!!.style = Paint.Style.STROKE
        mPaint!!.color = ContextCompat.getColor(context, R.color.color2)
        mPaint!!.strokeCap = Paint.Cap.ROUND
        mPaint!!.strokeJoin = Paint.Join.ROUND
        mPaint!!.strokeWidth = 10f
        mPath = Path()
        solutionCellsVisited = 0
        solvedMaze = false
        solved = ArrayList()
        for (i in solutionPath.indices) {
            solved!!.add(false)
        }
        // Log.d("SOLVED_LENGTH", Integer.toString(solved.size()));
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawPath(mPath!!, mPaint!!)
    }

    override fun onTouchEvent(@NonNull event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                mPath!!.moveTo(event.x, event.y)
                return true
            }
            MotionEvent.ACTION_MOVE -> mPath!!.lineTo(event.x, event.y)
            MotionEvent.ACTION_UP -> {}
            else -> return false
        }
        // Schedule a repaint
        invalidate()
        // Check if user solved the maze
        var xInCell: Boolean
        var yInCell: Boolean
        for (i in solutionPath.indices) {
            xInCell = event.x >= solutionPath[i][0] && event.x <= solutionPath[i][2]
            yInCell = event.y >= solutionPath[i][1] && event.y <= solutionPath[i][3]
            if (xInCell && yInCell) {
                solved!![i] = true
                if (!solved!!.contains(false) && !solvedMaze) {
                    val poseidonFragment = PoseidonFragment()
                    poseidonFragment.startGameSolvedAnimation()
                    Toast.makeText(this.context, R.string.athena, Toast.LENGTH_SHORT).show()
                    solvedMaze = true
                    return true // not sure it this line is necessary
                }
            }
        }
        return true
    }
}