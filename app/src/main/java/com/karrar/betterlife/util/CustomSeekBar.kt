package com.karrar.betterlife.util

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSeekBar
import androidx.core.content.ContextCompat
import com.karrar.betterlife.R
import kotlin.jvm.Synchronized

class CustomSeekBar(
    context: Context,
    attrs: AttributeSet
) : AppCompatSeekBar(context, attrs) {

    private var rect: Rect = Rect()
    private var paint: Paint = Paint()
    private var seekbarHeight: Int = 6

    @Synchronized
    override fun onDraw(canvas: Canvas) {
        // for seek bar line
        rect[0, height / 2 - seekbarHeight / 2, width] = height / 2 + seekbarHeight / 2
        paint.color = ContextCompat.getColor(context, R.color.black_30)
        canvas.drawRect(rect, paint)

        //for right side
        if (this.progress > 0) {
            rect[width / 2, height / 2 - seekbarHeight / 2, width / 2 + width / 200 * (progress)] =
                height / 2 + seekbarHeight / 2

            paint.color = ContextCompat.getColor(context, R.color.green)
            canvas.drawRect(rect, paint)
        }

        //for left side
        if (this.progress < 0) {
            rect[width / 2 - width / 200 * (-progress), height / 2 - seekbarHeight / 2, width / 2] =
                height / 2 + seekbarHeight / 2

            paint.color = ContextCompat.getColor(context, R.color.red)
            canvas.drawRect(rect, paint)
        }
        super.onDraw(canvas)
    }
}