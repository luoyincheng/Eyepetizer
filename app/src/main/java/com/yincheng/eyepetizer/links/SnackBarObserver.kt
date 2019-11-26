package com.yincheng.eyepetizer.links

import android.view.View
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.yincheng.eyepetizer.provider.ResultProcessType

class SnackBarObserver constructor(
    private val view: View?,
    private val processType: ResultProcessType
) :
    Observer<Any> {
    override fun onChanged(any: Any) {
        if (view == null) return
        Snackbar.make(view, "$any", Snackbar.LENGTH_LONG).show()
    }
}