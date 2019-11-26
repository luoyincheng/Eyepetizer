package com.yincheng.eyepetizer.helpers

import android.content.Context
import android.view.MotionEvent
import android.view.VelocityTracker
import android.view.ViewConfiguration
import kotlin.math.abs

object MotionEventUtils {
    var mViewConfiguration: ViewConfiguration? = null
    var mVelocityTracker: VelocityTracker? = null
    val mMinimumVelocity: Float = 3000.0f
    val mMaximumVelocity: Float = 24000.0f
    var initialMotionEventX: Float = 0.0f
    var initialMotionEventY: Float = 0.0f

    init {

    }

    fun isFastFling(context: Context, motionEvent: MotionEvent): Boolean {
        var result = false
        if (mViewConfiguration == null) mViewConfiguration = ViewConfiguration.get(context)
        if (mVelocityTracker == null) mVelocityTracker = VelocityTracker.obtain()
        if (mViewConfiguration != null && mVelocityTracker != null) {
            mVelocityTracker?.addMovement(motionEvent)
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    initialMotionEventY = motionEvent.x
                    initialMotionEventY = motionEvent.y
                }
                MotionEvent.ACTION_MOVE -> {
                    val velocityTracker = mVelocityTracker
                    val pointerId = motionEvent.getPointerId(0)
                    velocityTracker!!.computeCurrentVelocity(
                        1000,
                        mViewConfiguration?.scaledMaximumFlingVelocity?.toFloat()
                            ?: mMaximumVelocity
                    )
                    val velocityY = velocityTracker.getYVelocity(pointerId)
                    val velocityX = velocityTracker.getXVelocity(pointerId)
                    val mMinimumFlingVelocity =
                        mViewConfiguration?.scaledMinimumFlingVelocity?.toFloat()?.times(8)
                            ?: mMinimumVelocity // todo

                    if (abs(velocityY) > mMinimumFlingVelocity || abs(velocityX) > mMinimumFlingVelocity) {
                        result = true
                    }
                }
                MotionEvent.ACTION_UP -> {
                    val velocityTracker = mVelocityTracker
                    val pointerId = motionEvent.getPointerId(0)
                    velocityTracker!!.computeCurrentVelocity(
                        1000,
                        mViewConfiguration?.scaledMaximumFlingVelocity?.toFloat()
                            ?: mMaximumVelocity
                    )
                    val velocityY = velocityTracker.getYVelocity(pointerId)
                    val velocityX = velocityTracker.getXVelocity(pointerId)
                    val mMinimumFlingVelocity =
                        mViewConfiguration?.scaledMinimumFlingVelocity?.toFloat()?.times(8)
                            ?: mMinimumVelocity // todo

                    if (abs(velocityY) > mMinimumFlingVelocity || abs(velocityX) > mMinimumFlingVelocity) {
                        result = true
                    }
                    mVelocityTracker?.recycle()
                    mVelocityTracker = null
                }
                MotionEvent.ACTION_CANCEL -> {
                    mVelocityTracker?.recycle()
                    mVelocityTracker = null
                }
            }
        }
        return result
    }
}