package com.yincheng.eyepetizer.views.activities

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.yincheng.eyepetizer.helpers.SPHelper
import com.yincheng.eyepetizer.provider.SP_IS_LOGIN

abstract class BaseActivity : AppCompatActivity() {
    var isLogin: Boolean? = null

    init {
        isLogin = SPHelper.getBoolean(SP_IS_LOGIN)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun setContentView(view: View?) {
        super.setContentView(view)
    }
}