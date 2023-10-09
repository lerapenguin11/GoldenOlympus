package com.example.goldenolympus.custom

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import android.util.AttributeSet
import android.widget.TextView

@SuppressLint("AppCompatCustomView")
class GradientTextViewBlue : TextView {
    constructor(context: Context?) : super(context, null, -1) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs, -1) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    ) {
    }

    @SuppressLint("DrawAllocation")
    override fun onLayout(
        changed: Boolean, left: Int, top: Int, right: Int,
        bottom: Int
    ) {
        super.onLayout(changed, left, top, right, bottom)
        if (changed) {
            paint.shader = LinearGradient(0f, 0f, 0f, textSize,
                intArrayOf(
                    Color.parseColor("#ACF0FF"),
                    Color.parseColor("#0073DE")), null, Shader.TileMode.CLAMP)
        }
    }
}