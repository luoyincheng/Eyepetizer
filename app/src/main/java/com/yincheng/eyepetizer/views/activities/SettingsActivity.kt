package com.yincheng.eyepetizer.views.activities

import android.os.Bundle
import com.yincheng.eyepetizer.R
import com.yincheng.eyepetizer.helpers.SPHelper
import com.yincheng.eyepetizer.provider.SP_IS_LOGIN
import kotlinx.android.synthetic.main.activity_settings.*

class SettingsActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        window.setBackgroundDrawable(null)
        ftv_sign_out.setOnClickListener {
            SPHelper.putIgnoreResult(SP_IS_LOGIN, false)
        }
    }
}