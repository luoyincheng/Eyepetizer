package com.yincheng.closer.app

import android.app.Application
import com.yincheng.closer.helpers.TypeFaceHelper

class CloserApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
        TypeFaceHelper.generateAllAvailableTypefaces(this)
    }

    companion object {
        private var instance: CloserApplication? = null
        fun getApplication() = instance!!  // !!表示如果instance为空则抛出空指针异常
    }
}
