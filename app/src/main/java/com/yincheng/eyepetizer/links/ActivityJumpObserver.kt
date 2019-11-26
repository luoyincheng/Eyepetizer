package com.yincheng.eyepetizer.links

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import com.yincheng.eyepetizer.R

class ActivityJumpObserver constructor(
    private val activity: FragmentActivity,
    private val intent: Intent
) :
    Observer<Boolean> {
    override fun onChanged(isLogin: Boolean) {
        if (!isLogin) return
        activity.startActivity(intent)
        activity.overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out)
    }
}