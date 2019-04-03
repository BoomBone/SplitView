package com.ting.splitview

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * 粒子封装对象
 */
class SplitView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private lateinit var mPaint: Paint
    private lateinit var mBitmap: Bitmap
    private val mBalls: MutableList<Ball> = arrayListOf()
    private val d: Float = 3f

    init {
        mPaint = Paint()
        mBitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic)
        for (x in 0 until mBitmap.width) {
            for (y in 0 until mBitmap.height) {
                val color = mBitmap.getPixel(x, y)
                val ballX = x * d + d / 2
                val ballY = y * d + d / 2
                val ballR = d / 2
                val ball = Ball(color, ballX, ballY, ballR)
                mBalls.add(ball)
            }
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(500f,500f)
        for (ball in mBalls){
            mPaint.color = ball.color
            canvas?.drawCircle(ball.x,ball.y,ball.r,mPaint)
        }
    }
}