package com.yincheng.closer.widgets

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import com.google.android.material.snackbar.Snackbar
import com.yincheng.closer.R
import com.yincheng.closer.provider.DAY_OF_SECONDS
import com.yincheng.closer.provider.HOUR_OF_SECONDS
import com.yincheng.closer.provider.MINUTE_OF_SECONDS
import com.yincheng.closer.provider.Year_OF_SECONDS
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.*
import java.util.Calendar.getInstance
import java.util.concurrent.TimeUnit

class CountDownView @JvmOverloads constructor(
    mContext: Context,
    attrs: AttributeSet? = null,
    defAttrSet: Int = 0
) :
    FontTextView(mContext, attrs, defAttrSet), View.OnClickListener, DefaultLifecycleObserver {

    override fun onDestroy(lifecycleOwner: LifecycleOwner) {
//        countDownSeconds.removeObserver(mCountTimeObserver)
//        (mContext as AppCompatActivity).lifecycle.removeObserver(this)
        Log.i("CountDownView", "onDestroy")
        mDisposable?.dispose()
    }

    override fun onClick(v: View) {
        Snackbar.make(v, "点人家干嘛", Snackbar.LENGTH_LONG).show()
    }

    private var mDeadLine = getInstance()
    private var remainedTimeInSeconds: Long = 0
    private var mDisposable: Disposable? = null
//    private var countDownSeconds = MutableLiveData<Long>()
//    private val mCountTimeObserver = CountTimeObserver()

    init {
        this@CountDownView.setOnClickListener(this)
        require(mContext is AppCompatActivity) { "only support in AppcompatActivity now" }
        /**
         * 必须在gradle中添加依赖：kapt 'androidx.lifecycle:lifecycle-compiler:2.1.0' 否则：
         * Caused by: java.lang.IllegalArgumentException: The observer class has some methods that use newer APIs which are
         * not available in the current OS version. Lifecycles cannot access even other methods so you should make sure that
         * your observer classes only access framework classes that are available in your min API level OR use
         * lifecycle:compiler annotation processor.
         */
        mContext.lifecycle.addObserver(this)
    }

    fun setDeadLine(
        year: Int, month: Int, date: Int, hourOfDay: Int, minute: Int,
        second: Int
    ) {
        // month是从０开始的，需要减去１
        mDeadLine.set(year, month - 1, date, hourOfDay, minute, second)
        val differenceValue = mDeadLine.timeInMillis - Calendar.getInstance().timeInMillis
        if (differenceValue >= 0) {
            remainedTimeInSeconds = differenceValue / 1000
//            countDownSeconds.observe(context as LifecycleOwner, mCountTimeObserver)
//            startCountDown()
            startRxCountDown(remainedTimeInSeconds)
        }
    }

    private fun startRxCountDown(long: Long) {
        var value = long
        mDisposable = Observable
            .interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                value -= 1
                val years = "${value / Year_OF_SECONDS}"
                var days = "${value % Year_OF_SECONDS / DAY_OF_SECONDS}"
                var hours = "${value % DAY_OF_SECONDS / HOUR_OF_SECONDS}"
                var minutes = "${value % DAY_OF_SECONDS % HOUR_OF_SECONDS / MINUTE_OF_SECONDS}"
                var seconds = "${value % DAY_OF_SECONDS % HOUR_OF_SECONDS % MINUTE_OF_SECONDS}"
                if (days.length < 2) days = "0$days"
                if (hours.length < 2) hours = "0$hours"
                if (minutes.length < 2) minutes = "0$minutes"
                if (seconds.length < 2) seconds = "0$seconds"
                @SuppressLint("SetTextI18n")
                this@CountDownView.text = "$years:$days:$hours:$minutes:$seconds"
                var drawableLeft: Drawable? = null
                when ((seconds.toInt() % 2)) {
                    0 -> drawableLeft =
                        this@CountDownView.resources.getDrawable(R.drawable.ic_hourglass_full, null)
                    1 -> drawableLeft =
                        this@CountDownView.resources.getDrawable(
                            R.drawable.ic_hourglass_hollow,
                            null
                        )
                }
                if (drawableLeft != null) {
                    drawableLeft.setBounds(
                        0,
                        0,
                        drawableLeft.minimumWidth,
                        drawableLeft.minimumHeight
                    )
                    this@CountDownView.setCompoundDrawables(drawableLeft, null, null, null)
                }
            }
    }

//    private fun startCountDown() {
//        GlobalScope.launch {
//            for (i in remainedTimeInSeconds downTo 0) {
//                delay(1000)
//                countDownSeconds.postValue(i)
//            }
//        }
//    }
//
//    inner class CountTimeObserver : Observer<Long> {
//        override fun onChanged(value: Long) {
//            val years = "${value / Year_OF_SECONDS}"
//            var days = "${value % Year_OF_SECONDS / DAY_OF_SECONDS}"
//            var hours = "${value % DAY_OF_SECONDS / HOUR_OF_SECONDS}"
//            var minutes = "${value % DAY_OF_SECONDS % HOUR_OF_SECONDS / MINUTE_OF_SECONDS}"
//            var seconds = "${value % DAY_OF_SECONDS % HOUR_OF_SECONDS % MINUTE_OF_SECONDS}"
//            if (days.length < 2) days = "0$days"
//            if (hours.length < 2) hours = "0$hours"
//            if (minutes.length < 2) minutes = "0$minutes"
//            if (seconds.length < 2) seconds = "0$seconds"
//            @SuppressLint("SetTextI18n")
//            this@CountDownView.text = "$years:$days:$hours:$minutes:$seconds"
//            var drawableLeft: Drawable? = null
//            when ((seconds.toInt() % 2)) {
//                0 -> drawableLeft =
//                    this@CountDownView.resources.getDrawable(R.drawable.ic_hourglass_full, null)
//                1 -> drawableLeft =
//                    this@CountDownView.resources.getDrawable(R.drawable.ic_hourglass_hollow, null)
//            }
//            if (drawableLeft != null) {
//                drawableLeft.setBounds(0, 0, drawableLeft.minimumWidth, drawableLeft.minimumHeight)
//                this@CountDownView.setCompoundDrawables(drawableLeft, null, null, null)
////                    val drawableLeftAnimator =
////                        ObjectAnimator.ofInt(appCompatTextView.compoundDrawables[0], "level", 0, 10000)
////                    drawableLeftAnimator.duration = 400
////                    drawableLeftAnimator.start()
//            }
//        }
//    }
}