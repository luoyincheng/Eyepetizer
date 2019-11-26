package com.yincheng.closer.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import com.yincheng.closer.R

class ExponentView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defAttrSet: Int = 0
) :
    CloserView(context, attrs, defAttrSet) {

    private var pathWidth = 10f
    private var pathColor = Color.parseColor("#ffffff")
    private var dotColor = Color.parseColor("#ffffff")

    private val data: ArrayList<Float> by lazy {
        ArrayList<Float>().apply {
            add(1000f)
            add(945f)
            add(932f)
            add(911f)
            add(901f)
            add(924f)
            add(854f)
            add(812f)
            add(709f)
            add(734f)
            add(666f)
        }
    }

    private val polyLinePath: Path by lazy {
        Path().apply {
            // TODO()
        }
    }

    init {
        val typedArray =
            context.obtainStyledAttributes(attrs, R.styleable.ExponentView, defAttrSet, 0)
        pathWidth =
            typedArray.getDimension(R.styleable.ExponentView_ExponentViewPathWidth, pathWidth)
        pathColor = typedArray.getColor(R.styleable.ExponentView_ExponentViewPathColor, pathColor)
        dotColor = typedArray.getColor(R.styleable.ExponentView_ExponentViewDotColor, dotColor)
        typedArray.recycle()

        data.forEachIndexed { index, y ->
            if (index == 0) {
                polyLinePath.setLastPoint(0f, 1000f)
            } else {
                polyLinePath.lineTo(index * 100f, y)
            }
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        closerStrokePaint.color = pathColor
        closerStrokePaint.strokeWidth = pathWidth
        canvas?.drawPath(polyLinePath, closerStrokePaint)
        drawAllDots(canvas)
    }

    private fun drawAllDots(canvas: Canvas?) {
        closerFillPaint.color = dotColor
        closerFillPaint.style = Paint.Style.FILL
        data.forEachIndexed() { index, y ->
            canvas?.drawCircle(index * 100f, y, pathWidth, closerFillPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.i("fadfadfaf", "down")
            }
            MotionEvent.ACTION_MOVE -> {
            }
            MotionEvent.ACTION_UP -> {

            }
        }
        return true
    }
}