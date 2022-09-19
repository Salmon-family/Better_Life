package com.karrar.betterlife.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.karrar.betterlife.R
import kotlin.jvm.Synchronized

class CustomSeekBar : AppCompatSeekBar {
    private var rect: Rect? = null
    private var paint: Paint? = null
    private var seekbar_height = 0

    constructor(context: Context?) : super(context!!)
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        rect = Rect()
        paint = Paint()
        seekbar_height = 6
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    )

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        // for seek bar line
        rect!![0, height / 2 - seekbar_height / 2, width] =
            height / 2 + seekbar_height / 2
        paint!!.color = ContextCompat.getColor(context,R.color.black_30)
        canvas.drawRect(rect!!, paint!!)

        //for right side
        if (this.progress > 0) {

            rect!![width / 2, height / 2 - seekbar_height / 2, width / 2 + width / 200 * (progress)] =
                height / 2 + seekbar_height / 2

            paint!!.color = ContextCompat.getColor(context,R.color.green)
            canvas.drawRect(rect!!, paint!!)
        }

        //for left side
        if (this.progress < 0) {

            rect!![width / 2 - width / 200 * (-progress), height / 2 - seekbar_height / 2, width / 2] =
                height / 2 + seekbar_height / 2

            paint!!.color = ContextCompat.getColor(context,R.color.red)
            canvas.drawRect(rect!!, paint!!)
        }
        super.onDraw(canvas)
    }
}