package com.karrar.betterlife.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import kotlin.jvm.Synchronized

class CustomSeekBar : AppCompatSeekBar {
    private var rect: Rect? = null
    private var paint: Paint? = null
    private var seekbar_height = 0

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        rect = Rect()
        paint = Paint()
        seekbar_height = 6
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
    }

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        rect!![0 + thumbOffset, height / 2 - seekbar_height / 2, width - thumbOffset] =
            height / 2 + seekbar_height / 2
        paint!!.color = Color.GRAY
        canvas.drawRect(rect!!, paint!!)
        if (this.progress > 50) {
            rect!![width / 2, height / 2 - seekbar_height / 2, width / 2 + width / 100 * (progress - 50)] =
                height / 2 + seekbar_height / 2
            paint!!.color = Color.CYAN
            canvas.drawRect(rect!!, paint!!)
        }
        if (this.progress < 50) {
            rect!![width / 2 - width / 100 * (50 - progress), height / 2 - seekbar_height / 2, width / 2] =
                height / 2 + seekbar_height / 2
            paint!!.color = Color.CYAN
            canvas.drawRect(rect!!, paint!!)
        }
        super.onDraw(canvas)
    }
}