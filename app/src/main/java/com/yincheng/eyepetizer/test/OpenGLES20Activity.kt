package com.yincheng.eyepetizer.test

import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class OpenGLES20Activity : AppCompatActivity() {
    private var mGlSurfaceView: GLSurfaceView? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mGlSurfaceView = GLSurfaceView(this)
        setContentView(mGlSurfaceView)
    }
}