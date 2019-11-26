package com.yincheng.closer.links

import androidx.appcompat.widget.AppCompatImageView
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.yincheng.closer.provider.ResultProcessType

class ImageViewObserver constructor(
    private val appCompatImageView: AppCompatImageView?,
    private val processType: ResultProcessType
) :
    Observer<Any> {
    override fun onChanged(any: Any) {
        if (appCompatImageView == null) return
        when (processType) {
            ResultProcessType.IV_SHOW -> {
                if (any !is String) throw IllegalArgumentException("IV_SHOW只支持url，类型为String")
                Glide.with(appCompatImageView).load(any).into(appCompatImageView)
            }
            else -> {
            }
        }
    }
}