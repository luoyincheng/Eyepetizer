package com.yincheng.androidanim.links

import android.animation.Animator
import android.view.animation.Animation

interface SerialAnimListener : Animation.AnimationListener, Animator.AnimatorListener {

    fun onThisAnimEnd()

    override fun onAnimationRepeat(animation: Animation?) {
    }

    override fun onAnimationRepeat(animation: Animator?) {
    }

    override fun onAnimationEnd(animation: Animation?) {
        onThisAnimEnd()
    }

    override fun onAnimationEnd(animation: Animator?) {
        onThisAnimEnd()
    }

    override fun onAnimationStart(animation: Animation?) {
    }

    override fun onAnimationStart(animation: Animator?) {
    }

    override fun onAnimationCancel(animation: Animator?) {
    }
}