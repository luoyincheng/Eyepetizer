package com.yincheng.eyepetizer.widgets

import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * kotlin中 init{} companion object{} 和 构造方法 三者的执行顺序
 * companion object{} -> init{} -> 构造函数
 * 实际上 init{} 是在构造函数最前面执行的
 */
abstract class CloserView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) :
    View(context, attrs, defStyleAttr) {

    private val mStrokeWidth = 10f

    protected val closerFillPaint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#ffffff")
        }
    }

    protected val closerStrokePaint: Paint by lazy {
        Paint().apply {
            isAntiAlias = true
            color = Color.parseColor("#ffffff")
            style = Paint.Style.STROKE
            strokeWidth = this@CloserView.mStrokeWidth
        }
    }

    init {

    }
}