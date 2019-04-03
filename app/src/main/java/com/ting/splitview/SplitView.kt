package com.ting.splitview

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import android.view.animation.LinearInterpolator

/**
 * 粒子封装对象
 */
class SplitView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private var mPaint: Paint = Paint()
    private var mBitmap: Bitmap = BitmapFactory.decodeResource(resources, R.mipmap.pic)
    private val mBalls: MutableList<Ball> = arrayListOf()
    private val d: Float = 3f

    lateinit var mAnimator: ValueAnimator

    init {
        for (x in 0 until mBitmap.width) {
            for (y in 0 until mBitmap.height) {
                val color = mBitmap.getPixel(x, y)
                val ballX = x * d + d / 2
                val ballY = y * d + d / 2
                val ballR = d / 2

                //速度（-20，20）
                val ballVX = (Math.pow(-1.0, Math.ceil(Math.random() * 1000)) * 20 * Math.random()).toFloat()
                val ballVY = rangeInt(-15, 35).toFloat()
                //加速度
                val ballAX = 0f
                val ballAY = 0.98f

                val ball = Ball(color, ballX, ballY, ballR, ballVX, ballVY, ballAX, ballAY)
                mBalls.add(ball)
            }
        }

        //设置动画
        mAnimator = ValueAnimator.ofFloat(0f, 1f)
        mAnimator.repeatCount = -1
        mAnimator.duration = 2000
        mAnimator.interpolator = LinearInterpolator()
        mAnimator.addUpdateListener {
            updateBall()
            invalidate()
        }
    }

    private fun updateBall() {
        //更新粒子的位置
        for (ball in mBalls) {
            ball.x += ball.vX
            ball.y += ball.vY

            ball.vX += ball.aX
            ball.vY += ball.aY
        }
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.translate(500f, 500f)
        for (ball in mBalls) {
            mPaint.color = ball.color
            canvas?.drawCircle(ball.x, ball.y, ball.r, mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        if (event?.action == MotionEvent.ACTION_DOWN) {
            //做动画效果
            mAnimator.start()
        }
        return super.onTouchEvent(event)
    }

    private fun rangeInt(i: Int, j: Int): Int {
        val max = Math.max(i, j)
        val min = Math.min(i, j)
        //在0到（max-min）范围内变化，取大于x的最小整数 再随机
        return (min + Math.ceil(Math.random() * (max - min))).toInt()

    }
}