package com.yincheng.closer.views.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.databinding.DataBindingUtil
import com.githang.statusbar.StatusBarCompat
import com.yincheng.closer.R
import com.yincheng.closer.databinding.ActivityLauncherBinding
import com.yincheng.closer.helpers.SPHelper
import com.yincheng.closer.provider.SP_IS_LOGIN
import com.yincheng.closer.provider.SP_PASSWORD
import com.yincheng.closer.provider.SP_USER_NAME

class LauncherActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i("activityLifeCycle", "onCreate")
//        val binding: ActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)
//        Log.i("jnitest", stringFromJNI())
        val binding: ActivityLauncherBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_launcher)
//        binding.cdvMain.setDeadLine(2019, 10, 8, 12, 0, 0)
        binding.rbSettings.setOnClickListener {
            Log.i("onclick", "ccccccccccccc")
            startActivity(Intent(this@LauncherActivity, SettingsActivity::class.java))
//            overridePendingTransition(R.anim.translate_right_in, R.anim.translate_left_out)
        }
        window.setBackgroundDrawable(null)
        StatusBarCompat.setStatusBarColor(this, resources.getColor(android.R.color.black))
        binding.cdvMain.setDeadLine(2023, 3, 2, 12, 0, 0)
//        SerialAnim.create(this)
//            .withAnimation(iv_center_1, R.anim.slide_in_right)
//            .start()
    }

    override fun onStart() {
        super.onStart()
        Log.i("activityLifeCycle", "onStart")
    }

    override fun onPause() {
        super.onPause()
    }

    override fun finish() {
        super.finish()
    }

    override fun onResume() {
        super.onResume()
        Log.i("activityLifeCycle", "onResume")
//        val ivSerialTasks = findViewById<AppCompatImageView>(R.id.iv_serial_tasks)
//        val ivParallelTasks = findViewById<AppCompatImageView>(R.id.iv_parallel_tasks)
//        val tvTestSerialAnim = findViewById<AppCompatTextView>(R.id.tv_test_serial_anim)
//
//        val rotateObjectAnimator =
//            ObjectAnimator.ofInt(tvTestSerialAnim.compoundDrawablesRelative[0], "level", 0, 10000)
//        rotateObjectAnimator.duration = 50
//        rotateObjectAnimator.repeatCount = 10
//        rotateObjectAnimator.repeatMode = ValueAnimator.REVERSE
//
//        SerialAnim.create(this)
//            .withAnimation(ivSerialTasks, R.anim.slide_out_right)
//            .withAnimation(ivParallelTasks, R.anim.slide_out_left)
//            .withObjectAnimator(null, rotateObjectAnimator)
//            .withAllAnimEndListener(object : SerialAnim.AllAnimEndListener {
//                override fun onAllAnimEnd() {
//                    Log.i("animcycletest", "onAllAnimEnd")
//                }
//            })
//            .start()
    }

    fun navLogoClick(view: View) {
        SPHelper.removeIgnoreResult(SP_USER_NAME)
        SPHelper.removeIgnoreResult(SP_PASSWORD)
        SPHelper.removeIgnoreResult(SP_IS_LOGIN)
        // TODO() // 界面同时也要响应
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    companion object {

        /**
         * Used to load the 'name_of_lib' library on application startup.
         * 1.CMakeLists.txt中的add_library中需要添加
         * 2.CMakeLists.txt中的target_link_libraries需要添加
         */
        init {
            System.loadLibrary("name_of_lib")
        }
    }
}