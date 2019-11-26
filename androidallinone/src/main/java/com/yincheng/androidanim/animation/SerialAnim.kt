package com.yincheng.androidanim.animation

import android.animation.Animator
import android.content.Context
import android.util.Log
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.AnimRes
import androidx.annotation.NonNull
import com.yincheng.androidanim.links.SerialAnimListener
import java.util.*

class SerialAnim private constructor(private val mContext: Context) : SerialAnimListener {
    override fun onThisAnimEnd() {
        Log.i("animcycletest", "onThisAnimEnd")
        mAllAnim.removeFirst()
        mAllTarget.removeFirst()
        start()
    }

    private var mAllAnim = LinkedList<Any>()
    private var mAllTarget = LinkedList<Any?>()
    private var mAllAnimEndListener: AllAnimEndListener? = null

    companion object {
        fun create(context: Context): SerialAnim {
            return SerialAnim(context)
        }
    }

    fun withAnimation(@NonNull target: View, @AnimRes animId: Int): SerialAnim {
        val nowAnimation = AnimationUtils.loadAnimation(this.mContext, animId)
        nowAnimation.setAnimationListener(this)
        mAllAnim.add(nowAnimation)
        mAllTarget.add(target)
        return this@SerialAnim
    }

    fun withValueAnimator(
        @NonNull target: Any, startValue: Any,
        endValue: Any,
        duration: Long
    ): SerialAnim {
//        val valueAnimator = ValueAnimator.ofFloat()
//        val animatorListenerClass = SerialAnimatorListener()
//        valueAnimator.addUpdateListener { }
        return this@SerialAnim
    }

    fun withObjectAnimator(target: Any?, animator: Animator): SerialAnim {
        animator.addListener(this)
        mAllAnim.add(animator)
        mAllTarget.add(target)
        return this@SerialAnim
    }

    fun withAllAnimEndListener(allAnimEndListener: AllAnimEndListener): SerialAnim {
        this.mAllAnimEndListener = allAnimEndListener
        return this@SerialAnim
    }

    fun start() {
        if (mAllAnim.size > 0) {
            when (val animUnknown = mAllAnim.first) {
                is Animation -> {
                    (mAllTarget.first as View).startAnimation(animUnknown)
                    Log.i(
                        "animcycletest",
                        "animation: $animUnknown  ${mAllAnim.size} ${mAllTarget.size}"
                    )
                }
                is Animator -> {
                    Log.i(
                        "animcycletest",
                        "animator: $animUnknown  ${mAllAnim.size} ${mAllTarget.size}"
                    )
                    animUnknown.start()
                }
            }
        } else {
            mAllAnimEndListener?.onAllAnimEnd()
            Log.i("animcycletest", "endＯｎｌｙ: ${mAllAnim.size}")
        }
    }

    interface AllAnimEndListener {
        fun onAllAnimEnd()
    }
}