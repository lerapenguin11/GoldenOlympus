package com.example.goldenolympus.presentation.ui

import android.annotation.SuppressLint
import android.graphics.*
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import com.example.goldenolympus.R
import com.example.goldenolympus.databinding.FragmentPoseidonBinding
import com.example.goldenolympus.presentation.mazing.FingerLine
import com.example.goldenolympus.presentation.mazing.MazeView
import com.example.goldenolympus.utilits.replaceFragmentMain
import java.util.*

class PoseidonFragment : Fragment() {
    private var _binding : FragmentPoseidonBinding? = null
    private val binding get() = _binding!!
    private var seconds = 0
    private var isRunning = false
    private var wasRunning = false

    private lateinit var handler : Handler
    private lateinit var runnable: Runnable

    enum class Color {
        WHITE, GRAY, BLACK
    }

    var mMazeView: MazeView? = null
    var mFingerLine: FingerLine? = null
    var mazeSize = 0
    var displaymetrics = DisplayMetrics()
    val PADDING = 64
    val FAT_FINGERS_MARGIN = 60

    @SuppressLint("ClickableViewAccessibility", "UseRequireInsteadOfGet")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        requireActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics)
        _binding = FragmentPoseidonBinding.inflate(inflater, container, false)

        handler = Handler()

        runnable = Runnable {
            seconds++
            binding.tvSec.text = formatTime(seconds)
            handler.postDelayed(runnable, 1000)
        }

        startTimer()

        binding.icPause.setOnClickListener {
            pauseTimer()
        }

        val params: ViewGroup.LayoutParams = binding.mazeWrapper.getLayoutParams()
        params.height = Math.floor(displaymetrics.heightPixels * 0.7).toInt()
        binding.mazeWrapper.setLayoutParams(params)

        binding.newMazeButton.setOnClickListener(View.OnClickListener { createMaze() })
        binding.newMazeButton.performClick()
        binding.icArrow.setOnClickListener { replaceFragmentMain(MenuFragment()) }

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        if (wasRunning) {
            startTimer()
        }
    }

    override fun onPause() {
        super.onPause()
        wasRunning = isRunning
        pauseTimer()
    }

    private fun startTimer() {
        isRunning = true
        handler.postDelayed(runnable, 1000)
        binding.icPause.isEnabled = true
    }

    private fun pauseTimer() {
        isRunning = false
        handler.removeCallbacks(runnable)
        binding.icPause.isEnabled = false
    }

    private fun formatTime(seconds: Int): String {
        val minutes = (seconds % 3600) / 60
        val secs = seconds % 60
        return String.format("%02d:%02d", minutes, secs)
    }

    fun createMaze() {
        if (mMazeView != null) {
            (mMazeView!!.parent as ViewGroup).removeView(mMazeView)
        }
        if (mFingerLine != null) {
            (mFingerLine!!.parent as ViewGroup).removeView(mFingerLine)
        }
        mazeSize = 10
        mMazeView = MazeView(requireContext(), mazeSize)
        val solutionAreas = Array(mMazeView!!.lengthOfSolutionPath) {
            IntArray(
                4
            )
        }
        var currentVertexKey: Int
        val totalMazeWidth = displaymetrics.widthPixels - PADDING
        val cellSide = totalMazeWidth / mazeSize
        var row: Int
        var column: Int
        var topLeftX: Int
        var topLeftY: Int
        var bottomRightX: Int
        var bottomRightY: Int
        for (i in 0 until mMazeView!!.lengthOfSolutionPath) {
            currentVertexKey = mMazeView!!.listOfSolutionVertecesKeys[i]
            row = currentVertexKey / mazeSize
            column = currentVertexKey % mazeSize
            topLeftX = PADDING / 2 + column * cellSide - FAT_FINGERS_MARGIN
            topLeftY = PADDING / 2 + row * cellSide - FAT_FINGERS_MARGIN
            bottomRightX = PADDING / 2 + (column + 1) * cellSide + FAT_FINGERS_MARGIN
            bottomRightY = PADDING / 2 + (row + 1) * cellSide + FAT_FINGERS_MARGIN
            solutionAreas[i] = intArrayOf(topLeftX, topLeftY, bottomRightX, bottomRightY)
        }
        binding.mazeWrapper.addView(mMazeView)
        mFingerLine = FingerLine(requireContext(), null, solutionAreas)
        binding.mazeWrapper.addView(mFingerLine)
        val startCellArrowX =
            solutionAreas[mMazeView!!.lengthOfSolutionPath - 1][0] + 12 + FAT_FINGERS_MARGIN
        val startCellArrowY =
            solutionAreas[mMazeView!!.lengthOfSolutionPath - 1][1] + 100 + FAT_FINGERS_MARGIN

        binding.arrow.x = startCellArrowX.toFloat()
        binding.arrow.y = startCellArrowY.toFloat()
        binding.arrow.visibility = View.VISIBLE

        val endCellStrawberryX = solutionAreas[0][0] + 8 + FAT_FINGERS_MARGIN
        val endCellStrawberryY = solutionAreas[0][1] + 10 + FAT_FINGERS_MARGIN
        binding.strawberry.setX(endCellStrawberryX.toFloat())
        binding.strawberry.setY(endCellStrawberryY.toFloat())
        binding.strawberry.setVisibility(View.VISIBLE)
    }

    fun removeValueFromArray(array: Array<Int>, value: Int): Array<Int?> {
        val arrayList = ArrayList(Arrays.asList(*array))
        arrayList.removeAt(arrayList.indexOf(value))
        val newArray = arrayOfNulls<Int>(arrayList.size)
        arrayList.toArray(newArray)
        return newArray
    }
}