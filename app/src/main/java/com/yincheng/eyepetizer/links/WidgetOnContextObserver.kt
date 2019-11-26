package com.yincheng.eyepetizer.links

import android.content.Context
import androidx.lifecycle.Observer
import com.yincheng.eyepetizer.provider.ResultProcessType

class WidgetOnContextObserver constructor(
    private val context: Context,
    private val processType: ResultProcessType
) :
    Observer<Any> {
    override fun onChanged(any: Any) {
    }
}