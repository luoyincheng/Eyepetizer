package com.yincheng.eyepetizer.app

import android.app.Application
import com.yincheng.eyepetizer.helpers.TypeFaceHelper

class EyepetizerApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        TypeFaceHelper.generateAllAvailableTypefaces(this)
    }

    companion object {
        private var instance: EyepetizerApplication? = null
        fun getApplication() = instance!!  // !!表示如果instance为空则抛出空指针异常
    }
}
