package com.yincheng.closer.views.activities

import android.content.Intent
import android.os.Bundle
import com.yincheng.closer.R
import com.yincheng.closer.helpers.SPHelper
import com.yincheng.closer.provider.SP_IS_LOGIN

class SplashActivity : SurfaceAnimOneOffActivity() {
    //    private var disposable: Disposable? = null
//    private var tvCountDown: AppCompatTextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // todo
//        disposable = Observable
//            .timer(2000, TimeUnit.MILLISECONDS)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe {
//                when (isLogin) {
//                    true -> intent.setClass(this@SplashActivity, LauncherActivity::class.java)
//                    else -> intent.setClass(this@SplashActivity, SignInActivity::class.java)
//                }
////                startActivity(intent)
//            }
//
//        disposable = Observable
//            .interval(1, TimeUnit.SECONDS)
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe { int ->
//                tvCountDown?.text = int.toString()
//            }
//        pv?.setOnClickListener {
//
//
//        }
    }

//    override fun onPause() {
//        super.onPause()
//        disposable?.dispose()
//    }
//
//    override fun onStop() {
//        super.onStop()
//        disposable?.dispose()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        disposable?.dispose()
//    }

    override fun onPageAnimEnd() {
        super.onPageAnimEnd()
        val intent = Intent()
        when (SPHelper.getBoolean(SP_IS_LOGIN)) {
            true -> intent.setClass(this@SplashActivity, LauncherActivity::class.java)
            else -> intent.setClass(this@SplashActivity, SignInActivity::class.java)
        }
        startActivity(intent)
    }
}