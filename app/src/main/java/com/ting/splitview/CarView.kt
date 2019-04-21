package com.ting.splitview

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import android.view.View

/**
 * Created by Ting on 2019/4/21.
 * 小车滑动View
 */
class CarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    lateinit var carBitmap: Bitmap

    lateinit var path: Path
    //路径计算
    lateinit var pathMeasure: PathMeasure
    //进度百分比0-1
    private var distanceRatio = 0f
    //画圆圈的画笔
    lateinit var circlePaint: Paint
    //画小车的画笔
    lateinit var carPaint: Paint
    //针对car bitmap图片操作的矩阵
    lateinit var carMatrix: Matrix

    init {
        //将小车图片加载到内存
        carBitmap = BitmapFactory.decodeResource(resources, R.mipmap.icon_car)
        //画个圆
        path = Path()
        path.addCircle(0f, 0f, 200f, Path.Direction.CW)
        pathMeasure = PathMeasure(path, false)
        //绘制圆环
        circlePaint = Paint()
        circlePaint.strokeWidth = 5f
        circlePaint.style = Paint.Style.STROKE
        circlePaint.isAntiAlias = true
        circlePaint.color = Color.BLACK

        //绘制小车
        carPaint = Paint()
        carPaint.color = Color.DKGRAY
        carPaint.strokeWidth = 2f
        carPaint.style = Paint.Style.STROKE

        carMatrix = Matrix()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        //设置画布背景为白色
        canvas.drawColor(Color.WHITE)

        val cWidth = width.toFloat()
        val cHeight = height.toFloat()

        //移动canvas坐标系到中间
        canvas.translate(cWidth / 2, cHeight / 2)
        carMatrix.reset()
        //对当前的进度进行累加
        distanceRatio += 0.006f
        if (distanceRatio >= 1) {
            distanceRatio = 0f
        }
        //通过距离算角度

        //记录位置

        val pos = FloatArray(2)
        val tan = FloatArray(2)

        //距离
        val distance = pathMeasure.length * distanceRatio
        pathMeasure.getPosTan(distance, pos, tan)

        //计算小车本来要旋转的角度
        val degree = (Math.atan2(tan[1].toDouble(), tan[0].toDouble()) * 180 / Math.PI).toFloat()
        //设置旋转角度
        carMatrix.postRotate(degree, carBitmap.width / 2.toFloat(), carBitmap.height / 2.toFloat())
        //这里要将设置到小车的中心点
        carMatrix.postTranslate(pos[0] - carBitmap.width / 2f, pos[1] - carBitmap.height / 2f)

        canvas.drawPath(path, circlePaint)
        canvas.drawBitmap(carBitmap, carMatrix, carPaint)
        invalidate()
    }

}