package com.yincheng.closer.widgets

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import android.util.Log
import android.view.View

class ProgressIndicator @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val mBlockWidth = 8.0f
    private val mMimHeight = 6
    private val mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val mColor = Color.WHITE

    private var sThreadLocalRect: ThreadLocal<Rect>? = null
    private fun getEmptyTempRect(): Rect? {
        if (sThreadLocalRect == null) {
            sThreadLocalRect = ThreadLocal<Rect>()
        }
        var rect = sThreadLocalRect?.get()
        if (rect == null) {
            rect = Rect()
            sThreadLocalRect?.set(rect)
        }
        rect.setEmpty()
        return rect
    }

    init {
        mPaint.strokeWidth = 1.0f
        mPaint.color = mColor
        Log.i("fadfadfadf", "densityDpi: ${resources.displayMetrics.densityDpi}")
        Log.i("fadfadfadf", "density: ${resources.displayMetrics.density}")
        Log.i("fadfadfadf", "widthPixels: ${resources.displayMetrics.widthPixels}")

    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawLine(0.0f, 0.0f, measuredWidth.toFloat(), 0.0f, mPaint)
        canvas.drawRect(0.0f, 5.0f, mBlockWidth, mBlockWidth + 5.0f, mPaint)
//        canvas.drawLine(0.0f, 1.0f, measuredWidth.toFloat(), 2.0f, mPaint)
        canvas.drawLine(
            0.0f,
            measuredHeight.toFloat(),
            measuredWidth.toFloat(),
            measuredHeight.toFloat(),
            mPaint
        )
//        canvas.drawLine(
//            0.0f,
//            measuredHeight.toFloat() - 1.0f,
//            measuredWidth.toFloat(),
//            measuredHeight.toFloat(),
//            mPaint
//        )
    }

}