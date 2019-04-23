package com.ting.splitview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Region

class ProviceItem(private val path: Path) {

    /**
     * 绘制颜色
     */
    private var drawColor: Int = 0

    fun setDrawColor(drawColor: Int) {
        this.drawColor = drawColor
    }

    internal fun drawItem(canvas: Canvas, paint: Paint, isSelect: Boolean) {
        if (isSelect) {
            //            绘制内部的颜色
            paint.clearShadowLayer()
            paint.strokeWidth = 1f
            paint.style = Paint.Style.FILL
            paint.color = drawColor
            canvas.drawPath(path, paint)
            //            绘制边界

            paint.style = Paint.Style.STROKE
            val strokeColor = -0x2f170c
            paint.color = strokeColor
            canvas.drawPath(path, paint)
        } else {

            paint.strokeWidth = 2f
            paint.color = Color.BLACK
            paint.style = Paint.Style.FILL
            paint.setShadowLayer(8f, 0f, 0f, 0xffffff)
            canvas.drawPath(path, paint)

            //            绘制边界
            paint.clearShadowLayer()
            paint.color = drawColor
            paint.style = Paint.Style.FILL
            paint.strokeWidth = 2f
            canvas.drawPath(path, paint)
        }


    }

    fun isTouch(x: Float, y: Float): Boolean {
        val rectF = RectF()
        path.computeBounds(rectF, true)
        val region = Region()
        region.setPath(path, Region(rectF.left.toInt(), rectF.top.toInt(), rectF.right.toInt(), rectF.bottom.toInt()))
        return region.contains(x.toInt(), y.toInt())
    }

}
