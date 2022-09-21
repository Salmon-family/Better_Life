package com.karrar.betterlife.util

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Bitmap.createBitmap
import android.graphics.Canvas
import androidx.core.content.ContextCompat.getDrawable

fun vectorToBitmap(context: Context,drawableId: Int): Bitmap? {
    val drawable = getDrawable(context, drawableId) ?: return null
    val bitmap = createBitmap(
        drawable.intrinsicWidth, drawable.intrinsicHeight, Bitmap.Config.ARGB_8888
    ) ?: return null
    val canvas = Canvas(bitmap)
    drawable.setBounds(0, 0, canvas.width, canvas.height)
    drawable.draw(canvas)
    return bitmap
}