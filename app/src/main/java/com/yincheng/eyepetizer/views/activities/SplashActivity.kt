package com.yincheng.eyepetizer.views.activities

import android.content.Intent
import android.os.Bundle
import com.yincheng.eyepetizer.R
import kotlinx.android.synthetic.main.activity_splash.*

class SplashActivity : OneOffActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loopPlayVideo()
        initClickListener()
    }

    private fun loopPlayVideo() {
        with(video_view) {
            setVideoPath("android.resource://$packageName/${R.raw.splashvideo}")
            setOnPreparedListener {
                requestFocus()
                seekTo(0)
                start()
            }
            setOnCompletionListener {
                start()
            }
        }
    }

    private fun initClickListener() {
        with(tv_enter) {
            setOnClickListener {
                startActivity(
                    Intent(
                        this@SplashActivity,
                        LauncherActivity::class.java
                    )
                )
            }
        }
    }
}