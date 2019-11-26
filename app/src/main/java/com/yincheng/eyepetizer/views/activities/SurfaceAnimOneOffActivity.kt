package com.yincheng.eyepetizer.views.activities

import android.content.Intent

abstract class SurfaceAnimOneOffActivity : SurfaceAnimActivity() {
    override fun startActivity(intent: Intent?) {
        super.startActivity(intent)
        finish()
    }
}