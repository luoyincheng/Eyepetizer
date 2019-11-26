package com.yincheng.eyepetizer.links

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.Observer
import com.yincheng.eyepetizer.R
import com.yincheng.eyepetizer.provider.DAY_OF_SECONDS
import com.yincheng.eyepetizer.provider.HOUR_OF_SECONDS
import com.yincheng.eyepetizer.provider.MINUTE_OF_SECONDS
import com.yincheng.eyepetizer.provider.ResultProcessType

class TextViewObserver constructor(
    private val appCompatTextView: AppCompatTextView,
    private val processType: ResultProcessType
) :
    Observer<Any> {
    override fun onChanged(any: Any) {
        when (processType) {
            ResultProcessType.TV_COUNT_DOWN -> {
                if (any !is Long) throw IllegalArgumentException("倒计时只支持Long类型的数据")
                var days = "${any / DAY_OF_SECONDS}"
                var hours = "${any % DAY_OF_SECONDS / HOUR_OF_SECONDS}"
                var minutes = "${any % DAY_OF_SECONDS % HOUR_OF_SECONDS / MINUTE_OF_SECONDS}"
                var seconds = "${any % DAY_OF_SECONDS % HOUR_OF_SECONDS % MINUTE_OF_SECONDS}"
                if (days.length < 2) days = "0$days"
                if (hours.length < 2) hours = "0$hours"
                if (minutes.length < 2) minutes = "0$minutes"
                if (seconds.length < 2) seconds = "0$seconds"
                @SuppressLint("SetTextI18n")
                appCompatTextView.text = "$days:$hours:$minutes:$seconds"
                var drawableLeft: Drawable? = null
                when ((seconds.toInt() % 2)) {
                    0 -> drawableLeft =
                        appCompatTextView.resources.getDrawable(R.drawable.ic_hourglass_full, null)
                    1 -> drawableLeft =
                        appCompatTextView.resources.getDrawable(
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
                    appCompatTextView.setCompoundDrawables(drawableLeft, null, null, null)
//                    val drawableLeftAnimator =
//                        ObjectAnimator.ofInt(appCompatTextView.compoundDrawables[0], "level", 0, 10000)
//                    drawableLeftAnimator.duration = 400
//                    drawableLeftAnimator.start()
                }
            }
            ResultProcessType.TV_SHOW -> {
                appCompatTextView.text = "$any"
            }
            else -> {
            }
        }
    }
}