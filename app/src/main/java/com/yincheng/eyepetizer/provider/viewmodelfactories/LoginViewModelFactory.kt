package com.yincheng.eyepetizer.provider.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.yincheng.eyepetizer.helpers.SPHelper
import com.yincheng.eyepetizer.provider.SP_PASSWORD
import com.yincheng.eyepetizer.provider.SP_USER_NAME
import com.yincheng.eyepetizer.viewmodels.LoginViewModel

class LoginViewModelFactory : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return LoginViewModel(SPHelper.getString(SP_USER_NAME), SPHelper.getString(SP_PASSWORD)) as T
    }
}